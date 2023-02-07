package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.ViewSparesInwardDetailsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ViewSparesInwardDetailsInterface {

    //http://192.168.29.173/beta1/app/service_engineers.php?action=viewSparesInwardDetail&opt=1&id=2

    @GET("service_engineers.php")
    Call<ViewSparesInwardDetailsResponse> CallviewSparesInwardDetail(@Query("action")String action, @Query("opt")String opt, @Query("id")String id);
}
