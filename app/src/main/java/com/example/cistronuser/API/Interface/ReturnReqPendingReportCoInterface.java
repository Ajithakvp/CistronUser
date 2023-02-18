package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.ReturnReqPendingReportCoResponse;
import com.example.cistronuser.API.Response.SpareInwardCoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ReturnReqPendingReportCoInterface {

    //https://cistronsystems.in/beta1/app/service_engineers.php?action=returnReqPendingReport&empid=e367

    @GET("service_engineers.php")
    Call<ReturnReqPendingReportCoResponse> CallReport(@Query("action")String action, @Query("empid")String empid);
}
