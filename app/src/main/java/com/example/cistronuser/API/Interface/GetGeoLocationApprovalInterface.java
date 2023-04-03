package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.GetGeoLocationApprovalResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetGeoLocationApprovalInterface {

    // https://cistronsystems.in/beta1/app/developers.php?action=getGeolocationApprovalReq

    @GET("developers.php")
    Call<GetGeoLocationApprovalResponse>CallGetGeo(@Query("action")String action);
}
