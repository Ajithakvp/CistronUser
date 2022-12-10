package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.SalesQuoteProductsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SalesQuoteProductsInterFace {

   //192.168.29.173/beta1/app/sales_quote.php?action=getProductOfTheSeries&series=33

    @GET("sales_quote.php")
    Call<SalesQuoteProductsResponse> CallProduct(@Query("action")String action,@Query("series")String series);
}
