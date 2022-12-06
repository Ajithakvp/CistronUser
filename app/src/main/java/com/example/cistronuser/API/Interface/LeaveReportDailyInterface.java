package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.LeaveReportDailyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LeaveReportDailyInterface {
    //http://192.168.29.173/beta1/app/attendance_leave.php?action=getDailyLeaveRecord&date=2022-11-28&empid=all


    @GET("attendance_leave.php")
    Call<LeaveReportDailyResponse> callDaily(@Query("action")String action,@Query("date")String date,@Query("empid")String empid,@Query("opt_user")String opt_user);

}
