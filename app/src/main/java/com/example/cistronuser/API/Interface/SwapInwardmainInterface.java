package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.SwapInwardmainResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SwapInwardmainInterface {
    // https://cistronsystems.in/beta1/app/swap_inward_app.php?user=test

    @GET("swap_inward_app.php")
    Call<SwapInwardmainResponse> MainResponse(@Query("user") String user);
}
