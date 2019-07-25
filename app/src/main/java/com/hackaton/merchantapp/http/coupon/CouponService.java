package com.hackaton.merchantapp.http.coupon;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Path;

public interface CouponService {
    @DELETE("/api/coupon/code/{code}")
    Call<ResponseBody> verifyCode(@Path("code") String code);

}
