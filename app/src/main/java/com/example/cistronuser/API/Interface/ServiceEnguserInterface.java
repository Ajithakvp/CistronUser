package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.ServiceEnguserResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceEnguserInterface {

    //// https://cistronsystems.in/beta1/app/service_eng_app.php?user=test

    @GET("service_eng_app.php")
    Call<ServiceEnguserResponse> user(@Query("user")String user);
}
