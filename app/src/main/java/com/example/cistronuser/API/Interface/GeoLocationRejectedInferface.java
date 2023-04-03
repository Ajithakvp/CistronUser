package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.GeoLocationApprovalResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeoLocationRejectedInferface {


    //Same Response
    @GET("developers.php")
    Call<GeoLocationApprovalResponse>CallRejectedGeo(@Query("action")String action, @Query("empid")String empid, @Query("request_id")String request_id);
}
