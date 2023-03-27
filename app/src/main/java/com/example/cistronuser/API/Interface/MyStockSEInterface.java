package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.MyStockSEResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyStockSEInterface {

    //// https://cistronsystems.in/beta1/app/service_engineers.php?action=getSeriesData&empid=e0003

    @GET("service_engineers.php")
    Call<MyStockSEResponse>CallMystock(@Query("action")String action,@Query("empid")String empid);
}
