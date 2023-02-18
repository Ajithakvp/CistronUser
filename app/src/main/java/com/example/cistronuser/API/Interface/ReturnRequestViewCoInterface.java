package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.ReturnRequestViewCoResponse;
import com.example.cistronuser.API.Response.SpareInwardViewCoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ReturnRequestViewCoInterface {



    //https://cistronsystems.in/beta1/app/service_engineers.php?action=viewReturnReqPendingDetail&retReqId=36&opt=1
    @GET("service_engineers.php")
    Call<ReturnRequestViewCoResponse> CallReport(@Query("action")String action, @Query("retReqId")String retReqId, @Query("opt")String opt);
}
