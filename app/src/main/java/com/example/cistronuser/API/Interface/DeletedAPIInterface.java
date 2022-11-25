package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.DeleteResponse;
import com.example.cistronuser.API.Response.LeaveDetailsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DeletedAPIInterface {


    //http://192.168.29.173/beta1/app/attendance_leave.php?action=deleteLeave&empid=e367&leaveID=13

    @GET("attendance_leave.php")
    Call<DeleteResponse> CallDetails(@Query("action")String action, @Query("empid")String empid, @Query("leaveID")String leaveID);
}
