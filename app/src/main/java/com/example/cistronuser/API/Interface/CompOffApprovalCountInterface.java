package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.CompOffCountResponse;
import com.example.cistronuser.API.Response.CompOffRequestResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CompOffApprovalCountInterface {

    //attendance_leave.php?action=getCompoffForApprovalCount

    @GET("attendance_leave.php")
    Call<CompOffCountResponse> CallCompoffcount(@Query("action")String action);
}
