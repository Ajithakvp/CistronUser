package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Model.LoginuserModel;
import com.example.cistronuser.API.Response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginInterFace {

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginuserModel> getUserLogin(

            @Field("empid") String ID,
            @Field("pwd") String password,
             @Field("lat") Double Latitude,
            @Field("long") Double longtitude,
             @Field("place") String Addresss,
            @Field("devName") String devName
    );

}
