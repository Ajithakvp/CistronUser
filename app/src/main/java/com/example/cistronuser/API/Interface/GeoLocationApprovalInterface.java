package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.GeoLocationApprovalResponse;
import com.example.cistronuser.API.Response.GetGeoLocationApprovalResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeoLocationApprovalInterface {

    //https://cistronsystems.in/beta1/app/developers.php?action=approveGeolocationReq&empid=e367&request_id=1

    @GET("developers.php")
    Call<GeoLocationApprovalResponse> CallApprovalGeo(@Query("action")String action, @Query("empid")String empid, @Query("request_id")String request_id);

}
