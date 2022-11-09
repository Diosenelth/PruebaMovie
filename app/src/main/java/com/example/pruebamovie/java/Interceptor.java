package com.example.pruebamovie.java;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;

public class Interceptor implements okhttp3.Interceptor {
    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        HttpUrl url = request.url().newBuilder()
                .addQueryParameter("api_key", "cd221c9c9cdc3e0f8e4969a04e9dfc45")
                .addQueryParameter("language", "es")
                .addQueryParameter("countries", "CO")
                .build();

        request = request.newBuilder().url(url).build();
        return chain.proceed(request);
    }
}
