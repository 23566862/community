package com.example.demo.provider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.demo.dto.GitHubUser;
import com.example.demo.dto.accessToken;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/*github提供者
* 访问github网站回调获取数据*/
@Component
public class GitHubProvider {
    /*使用okhttp 请求响应获得 AccessToken*/
    public String getAccessToken(accessToken accessToken){
        System.setProperty("sun.net.client.defaultConnectTimeout", String
                .valueOf(100000));// （单位：毫秒）
        System.setProperty("sun.net.client.defaultReadTimeout", String
                .valueOf(100000)); // （单位：毫秒）

        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        String json = JSONArray.toJSONString(accessToken);
        RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url("https://github.com/login/oauth/access_token")
                    .post(body)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                String string = response.body().string();
                System.out.println("原来:"+string);
                String[] split = string.split("&");
                String token = split[0].split("=")[1];
                System.out.println("分隔后:"+token);
                return token;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
    }

    //根据accessToken获取用户
    public GitHubUser getGitHubUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.github.com/user")
                    .header("Authorization","token "+accessToken)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                String string = response.body().string();
                System.out.println("内容："+string);
                GitHubUser gitHubUser = JSON.parseObject(string, GitHubUser.class);
                System.out.println(gitHubUser);
                return gitHubUser;

        } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
    }
}
