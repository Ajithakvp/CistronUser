package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.SpareInwardCoResponse;
import com.example.cistronuser.API.Response.SpareInwardViewCoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SpareInwardViewCoInteface {

    //https://cistronsystems.in/beta1/app/service_engineers.php?action=viewSpareInwardDetail&reqId=2508&opt=1

    @GET("service_engineers.php")
    Call<SpareInwardViewCoResponse> CallReport(@Query("action")String action, @Query("reqId")String reqId,@Query("opt")String opt);

}
