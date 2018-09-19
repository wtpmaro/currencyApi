package pl.coderslab.service;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UrlConnection {

    UrlConnection(){}

    /** Method coverts JSON from URL to String
     *
     * @param url - url address from API
     * @return JSON in format String.
     * @throws IOException
     */
    public String run(String url) throws IOException {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
