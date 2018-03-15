package com.jshc.waveprogressbar.modles;

/**
 * Created by JinT on 2018/3/15 0015.
 */

public interface Fmodel {
    interface OndataCallback<T>{
        void onSuccessed(T data);
        void onFailed();
    }
    void getData();
}
