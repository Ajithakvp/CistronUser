package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Model.LoginuserModel;
import com.example.cistronuser.API.Response.LoginUserResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProfileUserDataInterface {

    //empid=e378&pwd=CIS@sys&place=&devName=
    @GET("login.php")
    Call<LoginuserModel>CalluserData(@Query("empid")String empid, @Query("pwd")String pwd,
                                     @Query("place")String place, @Query("devName")String devName);
}
