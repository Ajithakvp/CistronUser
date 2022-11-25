package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.DailyReportAttendanceResponse;
import com.example.cistronuser.API.Response.DailyReportUserAttendanceResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DailyAttendanceUserInterface {

   // http://192.168.29.173/beta1/app/attendance_leave.php?action=getEmployeeRecord


    @GET("attendance_leave.php")
    Call<DailyReportUserAttendanceResponse> callDailyUser(@Query("action")String action);
}
