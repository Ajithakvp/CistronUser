package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.ApprovalleaveRequestResponse;
import com.example.cistronuser.API.Response.CompOffRequestResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CompOffRequestInterface {

    //http://192.168.29.173/beta1/app/attendance_leave.php?action=compoffForApproval

    @GET("attendance_leave.php")
    Call<CompOffRequestResponse> CallCompoff(@Query("action")String action);
}
