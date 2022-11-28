package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.CompOffDeleteReqResponse;
import com.example.cistronuser.API.Response.CompOffreqApporvalResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CompOffDeletedReqinterface {


    //http://192.168.29.173/beta1/app/attendance_leave.php?action=deleteCompoffForApproval&compoffId=123
    @GET("attendance_leave.php")
    Call<CompOffDeleteReqResponse> CalDeleteReq(@Query("action")String action, @Query("compoffId")String compoffId);
}
