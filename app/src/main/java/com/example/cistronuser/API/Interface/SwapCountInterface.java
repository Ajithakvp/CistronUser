package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.SwapCountResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SwapCountInterface {


    //https://cistronsystems.in/beta1/app/swap_count.php?uid=69&user=e261

    @GET("swap_count.php")
    Call<SwapCountResponse> Count(@Query("user")String user);
}
