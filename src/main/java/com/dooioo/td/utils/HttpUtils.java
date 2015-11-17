package com.dooioo.td.utils;

import com.squareup.okhttp.*;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Http工具类，使用OkHttp封装常见Http请求
 */
public class HttpUtils {
    private final OkHttpClient client = new OkHttpClient();

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        HttpUtils httpUtils = new HttpUtils();
    }

    public String doPostJsonSync(String url, Map<String, String> headers,String jsonStr) throws IOException {
        Request request=new Request.Builder().post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"),jsonStr)).url(url).headers(Headers.of(headers)).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
    public Future<String> doPostJsonAsync(String url, Map<String, String> headers, String jsonStr) throws IOException {
        Request request=new Request.Builder().post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"),jsonStr)).url(url).headers(Headers.of(headers)).build();
        return dealAsyncRequest(request);
    }
    public String doPostFormSync(String url, Map<String, String> headers,Map<String,String> form) throws IOException {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        form.forEach(builder::add);
        Request request=new Request.Builder().post(builder.build()).headers(Headers.of(headers)).url(url).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
    public Future<String> doPostFormAsync(String url, Map<String, String> headers, Map<String,String> form){
        FormEncodingBuilder builder = new FormEncodingBuilder();
        form.forEach(builder::add);
        Request request=new Request.Builder().post(builder.build()).headers(Headers.of(headers)).url(url).build();
        return dealAsyncRequest(request);
    }

    public String doPutJsonSync(String url,Map<String, String> headers,String jsonStr) throws IOException {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json; charset=utf-8"),jsonStr);
        Request request=new Request.Builder().put(requestBody).headers(Headers.of(headers)).url(url).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
    public Future<String> doPutJsonAsync(String url, Map<String, String> headers, String jsonStr){
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json; charset=utf-8"),jsonStr);
        Request request=new Request.Builder().put(requestBody).headers(Headers.of(headers)).url(url).build();
        return dealAsyncRequest(request);
    }
    public String doDeleteJsonSync(String url,Map<String, String> headers,String jsonStr) throws IOException {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json; charset=utf-8"),jsonStr);
        Request request=new Request.Builder().delete(requestBody).headers(Headers.of(headers)).url(url).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
    public Future<String> doDeleteJsonAsync(String url, Map<String, String> headers, String jsonStr){
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json; charset=utf-8"),jsonStr);
        Request request=new Request.Builder().delete(requestBody).headers(Headers.of(headers)).url(url).build();
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
        final CompletableFuture future = new CompletableFuture();
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
