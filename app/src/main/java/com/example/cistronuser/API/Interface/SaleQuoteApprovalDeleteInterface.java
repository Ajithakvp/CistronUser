package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.SaleQuoteApprovalDeleteResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SaleQuoteApprovalDeleteInterface {

    //http://192.168.29.173/beta1/app/sales_quote.php?action=deleteOrderCall&oaRequest=3

    @GET("sales_quote.php")
    Call<SaleQuoteApprovalDeleteResponse> CallDelete(@Query("action")String action,@Query("oaRequest")String oaRequest);
}
