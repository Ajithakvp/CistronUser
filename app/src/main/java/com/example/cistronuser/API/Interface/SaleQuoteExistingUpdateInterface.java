package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.SaleQuoteExistingUpdateResponse;
import com.example.cistronuser.API.Response.SalesQuoteHospitalUpdateResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SaleQuoteExistingUpdateInterface {

   // http://192.168.29.173/beta1/app/sales_quote.php?action=getQuoteUpdates&quote=1

    @GET("sales_quote.php")
    Call<SaleQuoteExistingUpdateResponse> CallExistingUpdateList(@Query("action")String action, @Query("quote")String quote);

}
