package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.UserListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserListInterface {

    // http://localhost/cistron/getuser.php

    @GET("user_action.php")
    Call<UserListResponse> calluserlist(@Query("action")String action);

}
