package com.jshc.waveprogressbar.utils;

/**
 * Created by JinT on 2018/3/19 0019.
 */

public class JNIUtil {

    static {
        System.loadLibrary("JNItest");
    }
    public native String getMessage();
}
