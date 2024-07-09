package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.SwapListengResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SwapListInsertengInterface {

    // https://erp.cistronsystems.in/swaplist_eng_app.php?user=test&p=3037&s=1

    @GET("swaplist_eng_app")
    Call<SwapListengResponse> view(@Query("user")String user,
                                   @Query("p")String p,
                                   @Query("s")String s);
}
