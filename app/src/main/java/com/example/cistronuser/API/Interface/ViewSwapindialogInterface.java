package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.ViewSwapindialogResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ViewSwapindialogInterface {


    //// https://cistronsystems.in/beta1/app/viewswapeng_app.php?id=176&opt=1

    @GET("viewswapeng_app.php")
    Call<ViewSwapindialogResponse> view(@Query("id") String id,@Query("opt") String opt);

}
