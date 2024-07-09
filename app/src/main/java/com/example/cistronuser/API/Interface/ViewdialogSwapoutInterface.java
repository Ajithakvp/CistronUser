package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.ViewdialogSwapoutResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ViewdialogSwapoutInterface {


    //view_swap_outward_app.php?id=177&opt=1

    @GET("view_swap_outward_app.php")
    Call<ViewdialogSwapoutResponse>view(@Query("id") String id,@Query("opt") String opt);
}
