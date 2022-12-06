package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.DateDisableResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DateDisableInterface {


    // attendance_leave.php?empid=e367&action=getDisabledDates
     @GET("attendance_leave.php")
     Call<DateDisableResponse> calldisble(@Query("empid")String empid, @Query("action")String action);
}
