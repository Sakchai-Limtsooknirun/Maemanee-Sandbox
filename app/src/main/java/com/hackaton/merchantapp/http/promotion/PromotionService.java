package com.hackaton.merchantapp.http.promotion;



import com.hackaton.merchantapp.model.Promotion;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PromotionService {

    @POST("/api/promotion")
    Call<Promotion> postPtomotion(@Query("merchantId") int id,
                                  @Body Promotion body);

}
