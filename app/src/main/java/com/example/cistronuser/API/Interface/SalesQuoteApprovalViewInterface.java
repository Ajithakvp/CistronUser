package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.SalesQuoteApprovalViewResponse;
import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SalesQuoteApprovalViewInterface {


    //http://192.168.29.173/beta1/app/sales_quote.php?action=viewOrderCall&oaReqId=58

    @GET("sales_quote.php")
    Call<SalesQuoteApprovalViewResponse>CallViewApproval(@Query("action")String action,@Query("oaReqId")String oaReqId);


}
