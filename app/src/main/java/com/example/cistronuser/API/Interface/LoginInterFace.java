package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Model.LoginuserModel;
import com.example.cistronuser.API.Response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoginInterFace {

    @GET("user_action.php")
    Call<LoginuserModel> getUserLogin(

            @Query("action") String action,
            @Query("empid") String ID,
            @Query("pwd") String password,
            @Query("lat") Double Latitude,
            @Query("long") Double longtitude,
            @Query("place") String Addresss,
            @Query("devName") String devName,
            @Query("ip") String ip
    );


}
