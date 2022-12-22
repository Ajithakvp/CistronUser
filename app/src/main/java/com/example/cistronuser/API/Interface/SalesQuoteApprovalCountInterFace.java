package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.SalesQuoteApprovalCountResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SalesQuoteApprovalCountInterFace {

    //http://192.168.29.173/beta1/app/user_action.php?action=getApprovalCounts

    @GET("user_action.php")
    Call<SalesQuoteApprovalCountResponse> callCount(@Query("action")String action);
}
