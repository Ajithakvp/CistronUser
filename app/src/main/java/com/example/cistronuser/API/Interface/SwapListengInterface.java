package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.SwapListengResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SwapListengInterface {

    // https://cistronsystems.in/beta1/app/swaplist_eng_app.php?user=test

    @GET("swaplist_eng_app")
    Call<SwapListengResponse>view(@Query("user")String user);
}
