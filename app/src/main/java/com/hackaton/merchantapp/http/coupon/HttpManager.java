package com.hackaton.merchantapp.http.coupon;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManager {

    private static HttpManager instances;

    public static HttpManager getInstance() {
        if (instances == null)
            instances = new HttpManager();
        return instances;
    }

    private CouponService service;

    private HttpManager() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.101.44:9000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(CouponService.class);
    }

    public CouponService getService() {
        return service;
    }


}
