package cn.chatbot.api.domain.ai.service;

import cn.chatbot.api.domain.ai.IOpenAI;
import cn.chatbot.api.domain.ai.model.aggregates.AIAnswer;
import cn.chatbot.api.domain.ai.model.vo.Choices;
import com.google.gson.Gson;
import okhttp3.*;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class OpenAI implements IOpenAI {
    private Logger logger = LoggerFactory.getLogger(OpenAI.class);

    @Value("${chatbot-api.openAiKey}")
    private String openAiKey;

    @Override
    public String doChatGPT(String question) throws IOException {
        String apiUrl = "https://api.openai.com/v1/chat/completions";
        String proxyHost = "127.0.0.1";
        int proxyPort = 10808; // 替换为你的代理端口号
        Proxy proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(proxyHost, proxyPort));
        OkHttpClient client = new OkHttpClient().newBuilder()
                .proxy(proxy)
                .connectTimeout(90, TimeUnit.SECONDS)
                .readTimeout(90, TimeUnit.SECONDS) // 设置读取超时时间为30秒
                .build();
        MediaType mediaType = MediaType.parse("application/json");

        RequestBody body = RequestBody.create(mediaType, "{\r\n     \"model\": \"gpt-3.5-turbo\",\r\n     \"messages\": [{\"role\": \"user\", \"content\": \""+question+"\"}],\r\n     \"temperature\": 0.7\r\n   }");
        Request request = new Request.Builder()
                .url(apiUrl)
                .method("POST", body)
                .addHeader("Content-Type","application/json")
                .addHeader("Authorization", "Bearer "+openAiKey)
                .build();
        Response response = client.newCall(request).execute();

        if (response.code() == HttpStatus.SC_OK) {
            String jsonStr = response.body().string();
            Gson gson = new Gson();
            AIAnswer aiAnswer = gson.fromJson(jsonStr, AIAnswer.class);
            StringBuilder answers = new StringBuilder();
            List<Choices> choices = aiAnswer.getChoices();
            for (Choices choice: choices){
                answers.append(choice.getMessage().getContent());
            }

            return  answers.toString();
        } else {
           throw  new RuntimeException("api.openai.com Err code is"+ response.code());
        }

    }
}
