package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.SpareReqPendingReportViewDetailsCOResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SpareReqPendingReportViewDetailsCOInterface {
    //https://cistronsystems.in/beta1/app/service_engineers.php?action=viewSpareReqDetail&reqId=2508&opt=1

    @GET("service_engineers.php")
    Call<SpareReqPendingReportViewDetailsCOResponse>CallView(@Query("action")String  action,@Query("reqId")String  reqId,@Query("opt")String  opt);
}
