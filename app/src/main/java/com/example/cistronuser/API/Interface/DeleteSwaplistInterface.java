package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.DeleteSwaplistResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DeleteSwaplistInterface {


    @GET("del_swapList_app.php")
    Call<DeleteSwaplistResponse> delete(@Query("id")String id);
}
