package com.lmc.community.provider;

import com.lmc.community.dto.AccessTokenDTO;
import org.springframework.stereotype.Component;

@Component
public class GithubProvider {

    public String getAccesToken(AccessTokenDTO accessTokenDTO){
        public static final MediaType JSON
                = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        String post(String url, String json) throws IOException {
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                return response.body().string();
            }
        }
    }
}
