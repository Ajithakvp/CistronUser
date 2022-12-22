package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.SalesQuoteApprovalCountResponse;
import com.example.cistronuser.API.Response.SalesQuoteApprovalListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SalesQuoteApprovalListInterface {
   // http://192.168.29.173/beta1/app/sales_quote.php?action=getOrderCallforApproval

    @GET("sales_quote.php")
    Call<SalesQuoteApprovalListResponse> CallApprovalList(@Query("action")String action);
}
