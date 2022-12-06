package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.DailyReportAttendanceResponse;
import com.example.cistronuser.API.Response.MonthlyReportAttendanceResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MonthlyReportAttendanceInterFace {

//http://192.168.29.173/beta1/app/attendance_leave.php?action=attendanceReport&monthYear=November 2022&empid=e367&report=monthly

    @GET("attendance_leave.php")
    Call<MonthlyReportAttendanceResponse> callMonthlyReport(@Query("action")String action,
                                                            @Query("monthYear")String monthYear, @Query("empid")String empid,
                                                            @Query("reportFor")String report);
}
