package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.LeaveApprovelCountResponse;
import com.example.cistronuser.API.Response.WaitingExpenseCountInterface;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LeaveApprovelCountInterface {


    //http://192.168.29.173/beta1/app/attendance_leave.php?action=getLeaveForApprovalCount

    @GET("attendance_leave.php")
    Call<LeaveApprovelCountResponse> Callcount(@Query("action")String action);

}
