package cn.chatbot.api.test;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

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

        get.addHeader("cookie","\n" +
                "abtest_env=product; zsxq_access_token=5A3C60D1-F2F5-6DA7-4B4D-ABAEFD33609D_8FA8D35B92D744F1; zsxqsessionid=b83fdf1aa40edf5c7ce4caa554e17e16; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2251142422215444%22%2C%22first_id%22%3A%2218195a0a7a446f-0a693596827e478-26021b51-3686400-18195a0a7a5892%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%A4%BE%E4%BA%A4%E7%BD%91%E7%AB%99%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC%22%2C%22%24latest_referrer%22%3A%22https%3A%2F%2Fopen.weixin.qq.com%2F%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTgxOTVhMGE3YTQ0NmYtMGE2OTM1OTY4MjdlNDc4LTI2MDIxYjUxLTM2ODY0MDAtMTgxOTVhMGE3YTU4OTIiLCIkaWRlbnRpdHlfbG9naW5faWQiOiI1MTE0MjQyMjIxNTQ0NCJ9%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%2251142422215444%22%7D%2C%22%24device_id%22%3A%2218195a0a7a446f-0a693596827e478-26021b51-3686400-18195a0a7a5892%22%7D");
//        get.addHeader("Content-Type","application/json, text/plain, */*");
        get.addHeader("Content-Type", "application/json;charset=utf8");

       CloseableHttpResponse response = httpClient.execute(get);

       if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
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
        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/811242254182222/answer");

        post.addHeader("cookie","\n" +
                "abtest_env=product; zsxq_access_token=5A3C60D1-F2F5-6DA7-4B4D-ABAEFD33609D_8FA8D35B92D744F1; zsxqsessionid=b83fdf1aa40edf5c7ce4caa554e17e16; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2251142422215444%22%2C%22first_id%22%3A%2218195a0a7a446f-0a693596827e478-26021b51-3686400-18195a0a7a5892%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%A4%BE%E4%BA%A4%E7%BD%91%E7%AB%99%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC%22%2C%22%24latest_referrer%22%3A%22https%3A%2F%2Fopen.weixin.qq.com%2F%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTgxOTVhMGE3YTQ0NmYtMGE2OTM1OTY4MjdlNDc4LTI2MDIxYjUxLTM2ODY0MDAtMTgxOTVhMGE3YTU4OTIiLCIkaWRlbnRpdHlfbG9naW5faWQiOiI1MTE0MjQyMjIxNTQ0NCJ9%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%2251142422215444%22%7D%2C%22%24device_id%22%3A%2218195a0a7a446f-0a693596827e478-26021b51-3686400-18195a0a7a5892%22%7D");
        post.addHeader("Content-Type","application/json;charset=utf8");

        String paramJson = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"自己去百度！\\n\",\n" +
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
    public void base64(){
        String cronExpression = new String(Base64.getDecoder().decode("MC81MCAqICogKiAqID8="), StandardCharsets.UTF_8);
        System.out.println(cronExpression);
    }
    @Test
    public void test_chatGPT() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.openai.com/v1/completions");
        post.addHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Bearer 自行申请 https://beta.openai.com/overview");

        String paramJson = "{\"model\": \"text-davinci-003\", \"prompt\": \"帮我写一个java冒泡排序\", \"temperature\": 0, \"max_tokens\": 1024}";

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
}
