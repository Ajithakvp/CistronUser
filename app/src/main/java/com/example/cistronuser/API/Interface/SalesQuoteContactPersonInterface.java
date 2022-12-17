package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.SalesQuoteContactPersonResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SalesQuoteContactPersonInterface {

   // http://192.168.29.173/beta1/app/sales_quote.php?action=viewSalesQuote&quote_id=1

    @GET("sales_quote.php")
    Call<SalesQuoteContactPersonResponse> callContact(@Query("action")String action,@Query("quote_id")String quote_id);
}
