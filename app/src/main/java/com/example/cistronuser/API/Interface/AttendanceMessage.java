package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Model.AttendanceMessageModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AttendanceMessage {


    // http://192.168.29.173/beta1/app/attendance_leave.php?empid=e367&action=check_attend
    @GET("attendance_leave.php")
    Call<AttendanceMessageModel>CallAttendMsg(@Query("empid")String empid,@Query("action")String action);
}
