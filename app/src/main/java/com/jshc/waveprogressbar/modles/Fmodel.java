package com.jshc.waveprogressbar.modles;

import java.util.Map;

/**
 * Created by JinT on 2018/3/15 0015.
 */

public interface Fmodel {
    interface OndataCallback<T> {
        void onSuccessed(T data);

        void onFailed(Throwable e);
    }

    void getData(String url, Map<String, Object> map, OndataCallback callback);
}
