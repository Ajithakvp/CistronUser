package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.MyStockListSEResponse;
import com.example.cistronuser.API.Response.MyStockSESearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyStockSESearchInteface {

    //http://192.168.29.173/beta1/app/service_engineers.php?action=myStock&empid=e0003&search=
    @GET("service_engineers.php")
    Call<MyStockSESearchResponse> CallMystock(@Query("action")String action, @Query("empid")String empid, @Query("search")String search);

}
