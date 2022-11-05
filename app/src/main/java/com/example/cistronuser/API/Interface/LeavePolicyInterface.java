package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.AttendanceResponse;
import com.example.cistronuser.API.Response.LeavePolicyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LeavePolicyInterface {

    // http://192.168.29.173/beta1/app/attendance_leave.php?empid=e367&action=available_leave
    @GET("attendance_leave.php")
    Call<LeavePolicyResponse>callleavepolicy(@Query("empid")String empid,@Query("action")String action);
}
