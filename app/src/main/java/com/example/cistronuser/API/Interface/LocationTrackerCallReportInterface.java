package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.LocationTrackerCallReportResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LocationTrackerCallReportInterface {

    //https://cistronsystems.in/beta1/app/locationTracker.php?action=getTodaysWork&empid=e367

    @GET("locationTracker.php")
    Call<LocationTrackerCallReportResponse>CallReport(@Query("action")String action,@Query("empid")String empid);

}
