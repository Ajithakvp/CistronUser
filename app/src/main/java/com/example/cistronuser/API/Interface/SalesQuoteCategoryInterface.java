package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.SalesQuoteCategoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SalesQuoteCategoryInterface {

    //http://192.168.29.173/beta1/app/sales_quote.php?action=getAvailableCategory

    @GET("sales_quote.php")
    Call<SalesQuoteCategoryResponse>CallCategory(@Query("action")String action);
}
