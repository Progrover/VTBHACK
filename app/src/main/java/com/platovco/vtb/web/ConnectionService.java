package com.platovco.vtb.web;

import android.util.Log;

import androidx.annotation.NonNull;

import org.json.JSONException;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public interface ConnectionService {

    String BASE_URL = Url.SERVER_URL.getString();
    OkHttpClient client = new OkHttpClient();


    void onMessage(String message);

    default Request buildRequest(String urlApex, Object requestData)  {
        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"),
                JsonService.toJson(requestData));

        return defaultBuilder(urlApex, requestData)
                .post(body)
                .build();
    }

    default Request.Builder defaultBuilder(String urlApex, Object requestData) {
        HttpUrl.Builder httpBuilder = HttpUrl.parse(BASE_URL + urlApex).newBuilder();
        Request.Builder builder = new Request.Builder();
        HttpUrl url = httpBuilder.build();
        return builder.url(url);
    }

    default void sendRequest(Request request, Callback<ResponseBody> onResponse, Runnable... onFinished) {
        client.newCall(request).enqueue(new okhttp3.Callback() {

            @Override
            public void onFailure(@NonNull final Call call, @NonNull final IOException e) {
                e.printStackTrace();
                onMessage("Соединение с сервером нарушено");
                Arrays.stream(onFinished).forEach(Runnable::run);
            }

            @Override
            public void onResponse(@NonNull final Call call, @NonNull final Response response) {
                try (response; ResponseBody responseBody = response.body()) {
                    if (responseBody == null) {
                        onMessage("Ошибка");
                        return;
                    }

                    try {
                        onResponse.accept(response.code(), responseBody);
                    } catch (JSONException | IOException e) {
                        Log.i("connect: ", e.getMessage());
                    }
                }
                Arrays.stream(onFinished).forEach(Runnable::run);
            }
        });
    }

    default void runAsync(Runnable runnable) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(runnable);
        executorService.shutdown();
    }
}
