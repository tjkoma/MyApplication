package com.jshc.waveprogressbar.modles;

import com.jshc.waveprogressbar.beans.CookBean;
import com.jshc.waveprogressbar.services.ApiService;
import com.jshc.waveprogressbar.utils.RetrofitUtils;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by JinT on 2018/3/15 0015.
 */

public class FmodelImpl implements Fmodel {
    @Override
    public void getData(String url, Map<String, Object> map, final OndataCallback callback) {
        ApiService apiService = RetrofitUtils.getInstance().create(ApiService.class);
        Observable<CookBean> observable = apiService.getCookData(map);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CookBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CookBean value) {
                        callback.onSuccessed(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFailed(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
