package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.DashboardCallCountResponse;
import com.example.cistronuser.API.Response.ServiceDetailsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceDetailsInterFace {

    //http://192.168.29.173/beta1/app/service_engineers.php?action=getServiceDetail&callId=17289

    @GET("service_engineers.php")
    Call<ServiceDetailsResponse> CallServiceDetails(@Query("action")String action, @Query("callId")String callId);
}
