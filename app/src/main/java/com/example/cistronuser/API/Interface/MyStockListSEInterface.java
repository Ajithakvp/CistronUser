package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.MyStockListSEResponse;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyStockListSEInterface {

    // http://192.168.29.173/beta1/app/service_engineers.php?action=myStock&empid=e0003&seriesid=1

    @GET("service_engineers.php")
    Call<MyStockListSEResponse> CallMystock(@Query("action")String action, @Query("empid")String empid,@Query("seriesid")String seriesid);

}
