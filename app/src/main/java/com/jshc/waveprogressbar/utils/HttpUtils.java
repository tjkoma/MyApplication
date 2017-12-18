package com.jshc.waveprogressbar.utils;

import com.jshc.waveprogressbar.services.CallBackDataService;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by JinT on 2017/12/18 0018.
 */

public class HttpUtils {

    /**
     * GET请求
     *
     * @param getUrl
     * @param map
     * @param dataService
     */
    public static void getData(String getUrl, Map<String, Object> map, final CallBackDataService dataService) {
        OkHttpClient okHttpClient = new OkHttpClient();
        if (!map.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> entry = iterator.next();
                stringBuilder.append(entry.getKey() + "=" + entry.getValue() + "&");
            }
            String addUrl = stringBuilder.substring(0, stringBuilder.length() - 1);
            getUrl = getUrl + "?" + addUrl;
        }
        Request request = new Request.Builder()
                .get()
                .url(getUrl)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                dataService.Failured(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                dataService.Successed(response.body().string());
            }
        });
    }

    /**
     * POST请求
     *
     * @param postUrl
     * @param map
     * @param dataService
     */
    public static void postData(String postUrl, Map<String, Object> map, final CallBackDataService dataService) {
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        if (!map.isEmpty()) {
            Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> entry = iterator.next();
                builder.add(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }
        RequestBody requestBody = builder.build();
        final Request request = new Request.Builder()
                .post(requestBody)
                .url(postUrl)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                dataService.Failured(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                dataService.Successed(response.body().string());
            }
        });
    }
}
