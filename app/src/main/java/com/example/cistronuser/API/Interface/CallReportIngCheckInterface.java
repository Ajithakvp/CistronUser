package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.CallReportIngCheckResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CallReportIngCheckInterface {

    //https://cistronsystems.in/beta1/app/service_engineers.php?action=checkEmployee&empid=e345

    @GET("service_engineers.php")
    Call<CallReportIngCheckResponse>CalLCheck(@Query("action")String action,@Query("empid")String empid);
}
