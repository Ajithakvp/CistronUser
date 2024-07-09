package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.SwapInwardmainResponse;
import com.example.cistronuser.API.Response.SwapOutwardmainResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SwapOutwardMainInterface {

    // https://cistronsystems.in/beta1/app/swap_outward_app.php?user=test
    @GET("swap_outward_app.php")
    Call<SwapOutwardmainResponse> MainResponse(@Query("user") String user);
}
