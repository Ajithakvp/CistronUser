package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.AttendanceResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AttendanceInsert {

    //attendance_leave.php?empid=e367&place=&area=&action=new_attendance

    @GET("attendance_leave.php")
    Call<AttendanceResponse>callAttendInsert(@Query("empid")String empid,@Query("place")int place,
                                             @Query("area")String area,@Query("action")String action);
}
