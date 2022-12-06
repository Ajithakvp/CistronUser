package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.ApprovalleaveRequestResponse;
import com.example.cistronuser.API.Response.LeaveApprovalDeletedResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApprovalLeaveReqInterface {

    //http://192.168.29.173/beta1/app/attendance_leave.php?action=approveLeaveRequest&leaveId=123&lop=0&compoff=0


    @GET("attendance_leave.php")
    Call<ApprovalleaveRequestResponse> CallApproval(@Query("action")String action, @Query("leaveId")String leaveId, @Query("lop")String lop, @Query("compoff")String compoff,@Query("empid")String empid);
}
