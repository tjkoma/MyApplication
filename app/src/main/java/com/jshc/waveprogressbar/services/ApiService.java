package com.jshc.waveprogressbar.services;
import com.jshc.waveprogressbar.beans.CookBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by JinT on 2017/12/19 0019.
 */

public interface ApiService {
    @POST("cook/query.php")
    Observable<CookBean> getCookData(@QueryMap Map<String, Object> map);
}
