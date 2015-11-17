package com.dooioo.td.utils;

import com.google.gson.Gson;
import com.squareup.okhttp.*;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Http工具类，使用OkHttp封装常见Http请求
 */
public class HttpUtils {
    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();
    private final MediaType jsonType = MediaType.parse("application/json; charset=utf-8");

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        Map<String, String> map = new HashMap<>();
        Gson gson = new Gson();
        map.put("name", "11");
        System.out.println(gson.toJson(map));
        HttpUtils httpUtils = new HttpUtils();
    }

    public String doPostSync(String url, Map<String, String> headers, Map<String,String> nameValuePair) throws IOException {
        Request request = new Request.Builder().post(RequestBody.create(jsonType, gson.toJson(nameValuePair))).url(url).headers(Headers.of(headers)).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public Future<String> doPostAsync(String url, Map<String, String> headers, Map<String,String> nameValuePair) throws IOException {
        Request request = new Request.Builder().post(RequestBody.create(jsonType, gson.toJson(nameValuePair))).url(url).headers(Headers.of(headers)).build();
        return dealAsyncRequest(request);
    }

    public String doPostFormSync(String url, Map<String, String> headers, Map<String, String> form) throws IOException {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        form.forEach(builder::add);
        Request request = new Request.Builder().post(builder.build()).headers(Headers.of(headers)).url(url).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public Future<String> doPostFormAsync(String url, Map<String, String> headers, Map<String, String> form) {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        form.forEach(builder::add);
        Request request = new Request.Builder().post(builder.build()).headers(Headers.of(headers)).url(url).build();
        return dealAsyncRequest(request);
    }

    public String doPutSync(String url, Map<String, String> headers, Map<String,String> nameValuePair) throws IOException {
        RequestBody requestBody = RequestBody.create(jsonType, gson.toJson(nameValuePair));
        Request request = new Request.Builder().put(requestBody).headers(Headers.of(headers)).url(url).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public Future<String> doPutAsync(String url, Map<String, String> headers, Map<String,String> nameValuePair) {
        RequestBody requestBody = RequestBody.create(jsonType, gson.toJson(nameValuePair));
        Request request = new Request.Builder().put(requestBody).headers(Headers.of(headers)).url(url).build();
        return dealAsyncRequest(request);
    }

    public String doDeleteSync(String url, Map<String, String> headers, Map<String,String> nameValuePair) throws IOException {
        RequestBody requestBody = RequestBody.create(jsonType, gson.toJson(nameValuePair));
        Request request = new Request.Builder().delete(requestBody).headers(Headers.of(headers)).url(url).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public Future<String> doDeleteAsync(String url, Map<String, String> headers, Map<String,String> nameValuePair) {
        RequestBody requestBody = RequestBody.create(jsonType, gson.toJson(nameValuePair));
        Request request = new Request.Builder().delete(requestBody).headers(Headers.of(headers)).url(url).build();
        return dealAsyncRequest(request);
    }

    public String doGetSync(String url, Map<String, String> headers) throws IOException {
        Request request = new Request.Builder().get().headers(Headers.of(headers)).url(url).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String doGetSync(String url) throws IOException {
        return doGetSync(url, Collections.EMPTY_MAP);
    }

    public Future<String> doGetAsync(String url) {
        return doGetAsync(url, Collections.EMPTY_MAP);
    }

    public Future<String> doGetAsync(String url, Map<String, String> headers) {
        Request request = new Request.Builder().get().headers(Headers.of(headers)).url(url).build();
        return dealAsyncRequest(request);
    }

    public Future<String> dealAsyncRequest(final Request request) {
        final CompletableFuture<String> future = new CompletableFuture<>();
        client.newCall(request).enqueue(new Callback() {
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            public void onResponse(Response response) throws IOException {
                future.complete(response.body().string());
            }
        });
        return future;
    }
}
