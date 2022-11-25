package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.AdjustmentExpensesResponse;
import com.example.cistronuser.API.Response.DailyReportAttendanceResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DailyReportAttendanceInterFace {

    //http://192.168.29.173/beta1/app/attendance_leave.php?action=attendanceReport&date=2022-11-24&empid=all&report=daily

    @GET("attendance_leave.php")
    Call<DailyReportAttendanceResponse> callDailyReport(@Query("action")String action,
                                                        @Query("date")String date, @Query("empid")String empid,
                                                        @Query("report")String report);
}
