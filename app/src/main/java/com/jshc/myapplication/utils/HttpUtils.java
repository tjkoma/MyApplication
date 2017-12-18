package com.jshc.myapplication.utils;

import com.jshc.myapplication.services.CallBackDataService;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by JinT on 2017/12/18 0018.
 */

public class HttpUtils {

    /**
     * GET请求
     *
     * @param geturl
     * @param map
     * @param dataService
     */
    public static void getData(String geturl, Map<String, Object> map, final CallBackDataService dataService) {
        OkHttpClient okHttpClient = new OkHttpClient();
        if (!map.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> entry = iterator.next();
                stringBuilder.append(entry.getKey() + "=" + entry.getValue() + "&");
            }
            String addUrl = stringBuilder.substring(0, stringBuilder.length() - 1);
            geturl = geturl + "?" + addUrl;
        }
        Request request = new Request.Builder()
                .get()
                .url(geturl)
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
