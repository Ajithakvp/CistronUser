package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.CompOffreqApporvalResponse;
import com.example.cistronuser.API.Response.LeaveApprovalDeletedResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CompoffReqApprovalInterface {

    //http://192.168.29.173/beta1/app/attendance_leave.php?action=approveCompoffForApproval&compoffId=123&empid=e367

    @GET("attendance_leave.php")
    Call<CompOffreqApporvalResponse> CalApprovalReq(@Query("action")String action, @Query("compoffId")String compoffId, @Query("empid")String empid);
}
