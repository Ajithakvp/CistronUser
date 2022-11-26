package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.LeaveApprovalRejectedResponse;
import com.example.cistronuser.API.Response.LeaveApprovelCountResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LeaveApprovalRejectedInterface {

    //http://192.168.29.173/beta1/app/attendance_leave.php?action=rejectLeaveRequestByAdmin&leaveId=16


    @GET("attendance_leave.php")
    Call<LeaveApprovalRejectedResponse> CallRejected(@Query("action")String action,@Query("leaveId")String leaveId);
}
