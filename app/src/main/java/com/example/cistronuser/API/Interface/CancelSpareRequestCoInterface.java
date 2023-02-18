package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.CancelSpareRequestCoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CancelSpareRequestCoInterface {
    //service_engineers.php?action=cancelSpareReq&reqId=2508&linkReqId=2513&opt=1

    @GET("service_engineers.php")
    Call<CancelSpareRequestCoResponse>CallCancel(@Query("action")String action,@Query("reqId")String reqId,
                                                 @Query("linkReqId")String linkReqId,@Query("opt")String opt);
}
