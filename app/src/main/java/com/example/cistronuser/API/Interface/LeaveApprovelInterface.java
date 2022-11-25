package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.leaveApprovelResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LeaveApprovelInterface {

    //http://192.168.29.173/beta1/app/attendance_leave.php?action=leaveForApproval


    @GET("attendance_leave.php")
    Call<leaveApprovelResponse>callLeaveApprovel(@Query("action")String action);
}
