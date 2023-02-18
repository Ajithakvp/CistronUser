package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.SpareReqPendingReportCoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SpareReqPendingReportCoInterface {



    //https://cistronsystems.in/beta1/app/service_engineers.php?action=spareReqPendingReport&empid=e334

    @GET("service_engineers.php")
    Call<SpareReqPendingReportCoResponse> CallReport(@Query("action")String action,@Query("empid")String empid);
}
