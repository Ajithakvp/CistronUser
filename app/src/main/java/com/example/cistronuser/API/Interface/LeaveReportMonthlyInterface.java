package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.LeaveReportDailyResponse;
import com.example.cistronuser.API.Response.LeaveReportMonthlyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LeaveReportMonthlyInterface {

  //192.168.29.173/beta1/app/sample.php?action=getMonthlyLeaveRecord&monthYear=October 2022&empid=e367

    @GET("attendance_leave.php")
    Call<LeaveReportMonthlyResponse> callMonthly(@Query("action")String action, @Query("monthYear")String monthYear, @Query("empid")String empid);
}
