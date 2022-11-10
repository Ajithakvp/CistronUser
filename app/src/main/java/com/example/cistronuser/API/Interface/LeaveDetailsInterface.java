package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Model.LeaveFormAllocatedleave;
import com.example.cistronuser.API.Response.LeaveDetailsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LeaveDetailsInterface {

    //http://192.168.29.173/beta1/app/attendance_leave.php?action=cancelledLeaveReqs&empid=e367

    @GET("attendance_leave.php")
    Call<LeaveDetailsResponse>CallDetails(@Query("action")String action, @Query("empid")String empid);

}
