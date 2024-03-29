package com.lmc.community.provider;

import com.alibaba.fastjson.JSON;
import com.lmc.community.dto.AccessTokenDTO;
import com.lmc.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component//把普通pojo实例化到spring容器中
public class GithubProvider {

    public String getAccesToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType= MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

            RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
            Request request = new Request.Builder()
                    .url("https://github.com/login/oauth/access_token")
                    .post(body)
                    .build();
        System.out.println("cccccccccccccc");
            try (Response response = client.newCall(request).execute()) {
                String string = response.body().string();
                String token = string.split("&")[0].split("=")[1];
                System.out.println(token);
                return token;
            } catch (IOException e) {
            }
            return  null;

    }

    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.github.com/user?access_token="+accessToken)
                    .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            System.out.println("String========="+string);
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
        }
        return null;

    }
}
