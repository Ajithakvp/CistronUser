package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.LeaveApprovalDeletedResponse;
import com.example.cistronuser.API.Response.LeaveApprovalRejectedResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LeaveApprovalDeleteInterface {
    //http://192.168.29.173/beta1/app/attendance_leave.php?action=deleteLeaveRequestByAdmin&leaveId=16

    @GET("attendance_leave.php")
    Call<LeaveApprovalDeletedResponse> CallDeleted(@Query("action")String action, @Query("leaveId")String leaveId, @Query("empid")String empid);
}
