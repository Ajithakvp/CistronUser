package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.CompOffreqApporvalResponse;
import com.example.cistronuser.API.Response.CompoffReqRejectedResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CompOffReqRejectedInterface {

    //http://192.168.29.173/beta1/app/attendance_leave.php?action=rejectCompoffForApproval&compoffId=123&empid=e327&attdate=2022-11-10


    @GET("attendance_leave.php")
    Call<CompoffReqRejectedResponse> CalRejectedReq(@Query("action")String action, @Query("compoffId")String compoffId, @Query("empid")String empid, @Query("compoffDate")String compoffDate);
}
