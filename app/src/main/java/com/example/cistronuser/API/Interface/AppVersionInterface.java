package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.AppVersionResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AppVersionInterface {

    //http://192.168.29.173/beta1/app/user_action.php?action=getAppVersion
    @GET("user_action.php")
    Call<AppVersionResponse> callVersion(@Query("action")String action);
}
