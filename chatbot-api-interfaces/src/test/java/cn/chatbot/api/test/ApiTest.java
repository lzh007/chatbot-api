package cn.chatbot.api.test;


import cn.chatbot.api.domain.ai.model.aggregates.AIAnswer;
import cn.chatbot.api.domain.ai.model.vo.Choices;
import com.google.gson.Gson;
import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import com.plexpt.chatgpt.util.Proxys;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.TimeUnit;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *  测试
 */
public class ApiTest {
    //待回答问题
    @Test
    public void query_unanswered_questions() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        //15281288451882

        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/15281288451882/topics?scope=unanswered_questions&count=20");

        get.addHeader("cookie", "\n" +
                "abtest_env=product; zsxq_access_token=5A3C60D1-F2F5-6DA7-4B4D-ABAEFD33609D_8FA8D35B92D744F1; zsxqsessionid=b83fdf1aa40edf5c7ce4caa554e17e16; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2251142422215444%22%2C%22first_id%22%3A%2218195a0a7a446f-0a693596827e478-26021b51-3686400-18195a0a7a5892%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%A4%BE%E4%BA%A4%E7%BD%91%E7%AB%99%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC%22%2C%22%24latest_referrer%22%3A%22https%3A%2F%2Fopen.weixin.qq.com%2F%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTgxOTVhMGE3YTQ0NmYtMGE2OTM1OTY4MjdlNDc4LTI2MDIxYjUxLTM2ODY0MDAtMTgxOTVhMGE3YTU4OTIiLCIkaWRlbnRpdHlfbG9naW5faWQiOiI1MTE0MjQyMjIxNTQ0NCJ9%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%2251142422215444%22%7D%2C%22%24device_id%22%3A%2218195a0a7a446f-0a693596827e478-26021b51-3686400-18195a0a7a5892%22%7D");
//        get.addHeader("Content-Type","application/json, text/plain, */*");
        get.addHeader("Content-Type", "application/json;charset=utf8");

        CloseableHttpResponse response = httpClient.execute(get);

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    //回答问题
    @Test
    public void answer() throws IOException {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        //811242254182222    811242254182222
        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/588545515225154/answer");

        post.addHeader("cookie", "\n" +
                "abtest_env=product; zsxq_access_token=5A3C60D1-F2F5-6DA7-4B4D-ABAEFD33609D_8FA8D35B92D744F1; zsxqsessionid=b83fdf1aa40edf5c7ce4caa554e17e16; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2251142422215444%22%2C%22first_id%22%3A%2218195a0a7a446f-0a693596827e478-26021b51-3686400-18195a0a7a5892%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%A4%BE%E4%BA%A4%E7%BD%91%E7%AB%99%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC%22%2C%22%24latest_referrer%22%3A%22https%3A%2F%2Fopen.weixin.qq.com%2F%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTgxOTVhMGE3YTQ0NmYtMGE2OTM1OTY4MjdlNDc4LTI2MDIxYjUxLTM2ODY0MDAtMTgxOTVhMGE3YTU4OTIiLCIkaWRlbnRpdHlfbG9naW5faWQiOiI1MTE0MjQyMjIxNTQ0NCJ9%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%2251142422215444%22%7D%2C%22%24device_id%22%3A%2218195a0a7a446f-0a693596827e478-26021b51-3686400-18195a0a7a5892%22%7D");
        post.addHeader("Content-Type", "application/json;charset=utf8");

        String paramJson = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"当保安！\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"silenced\": false\n" +
                "  }\n" +
                "}";
        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }


    @Test
    public void base64() {
        String cronExpression = new String(Base64.getDecoder().decode("MC81MCAqICogKiAqID8="), StandardCharsets.UTF_8);
        System.out.println(cronExpression);
    }

    //调用ChatGPT

    @Test
    public void test_chatGPT() throws IOException {
        String apiUrl = "https://api.openai.com/v1/chat/completions";
        String apiKey = "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";

//        Proxy proxy = Proxys.http("127.0.0.1", 10808);
        String proxyHost = "127.0.0.1";
        int proxyPort = 10808; // 替换为你的代理端口号
//        Proxy proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress("123.0.0.1", proxyPort));
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 设置代理
        HttpHost proxy = new HttpHost(proxyHost, proxyPort, "http"); // 指定协议
        RequestConfig config = RequestConfig.custom()
                .setProxy(proxy)
                .setConnectTimeout(20000)
                .build();

        HttpPost post = new HttpPost(apiUrl);
        post.setConfig(config);
        post.addHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Bearer " + apiKey);

        String paramJson = "{\n" +
                "     \"model\": \"gpt-3.5-turbo\",\n" +
                "     \"messages\": [{\"role\": \"user\", \"content\": \"帮我写一个java冒泡排序!\"}],\n" +
                "     \"temperature\": 0.7\n" +
                "   }";
//        String paramJson = "{\"model\": \"gpt-3.5-turbo\", \"prompt\": \"帮我写一个java冒泡排序\", \"temperature\": 0.7, \"max_tokens\": 1024}";
        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }

    }

    @Test
    public void test_do() throws IOException {
        String apiUrl = "https://api.openai.com/v1/chat/completions";
        String apiKey = "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";
        String proxyHost = "127.0.0.1";
        int proxyPort = 10808; // 替换为你的代理端口号
        Proxy proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(proxyHost, proxyPort));
        OkHttpClient client = new OkHttpClient().newBuilder().proxy(proxy).connectTimeout(30, TimeUnit.SECONDS)
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n     \"model\": \"gpt-3.5-turbo\",\r\n     \"messages\": [{\"role\": \"user\", \"content\": \"Say this is a test!\"}],\r\n     \"temperature\": 0.7\r\n   }");
        Request request = new Request.Builder()
                .url(apiUrl)
                .method("POST", body)
                .addHeader("Content-Type","application/json")
                .addHeader("Authorization", "Bearer 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")
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

            System.out.println("Response Body: " + answers.toString());
        } else {
            System.out.println("Response Code: " + response.code());
            System.out.println("Response Body: " + response.body().string());
        }
    }

}
