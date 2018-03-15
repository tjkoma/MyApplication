package com.jshc.waveprogressbar.presenters;

import com.jshc.waveprogressbar.modles.Fmodel;
import com.jshc.waveprogressbar.views.Fview;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tjjunlintx on 2018/3/14.
 */

public class FpresenterImpl<T> implements Fpresenter, Fmodel.OndataCallback<T> {
    private Fview fview;
    private Fmodel fmodel;

    public FpresenterImpl(Fview fview, Fmodel fmodel) {
        this.fview = fview;
        this.fmodel = fmodel;
    }

    @Override
    public void getRequstData(String url, Map<String, Object> map) {
        fmodel.getData(url, map, this);
    }

    @Override
    public void onSuccessed(T data) {
        fview.onSuccessed(data);
    }

    @Override
    public void onFailed(Throwable e) {

    }


}
