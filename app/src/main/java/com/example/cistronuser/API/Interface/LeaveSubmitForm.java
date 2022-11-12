package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.leavesubmitresponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface LeaveSubmitForm {


    ///attendance_leave.php?action=applyLeave&empid=e367&code=XXX&reason=YYYY&dates=ZZZZ&type=AAA&lop=BBB&compoff=CCC

    @GET("attendance_leave.php")
    Call<leavesubmitresponse> callLeaveformsubmit(@Query("action")String action,@Query("empid")String empid, @Query("code")int code,
                                                  @Query("reason")int  reason, @Query("dates")String dates,
                                                  @Query("ful_or_half")int ful_or_half, @Query("lop") int lop, @Query("compoff")int compoff
                                              );


    @Multipart
    @POST("attendance_leave.php")
    Call<leavesubmitresponse> callLeaveformsubmitWithDocumentAPI(@Part("action") RequestBody action,
                                                                 @Part("empid") RequestBody empid,
                                                                 @Part("code") RequestBody code,
                                                                 @Part("reason") RequestBody reason,
                                                                 @Part("dates") RequestBody dates,
                                                                 @Part("ful_or_half") RequestBody ful_or_half,
                                                                 @Part("lop") RequestBody lop,
                                                                 @Part("compoff") RequestBody compoff,
                                                                 @Part MultipartBody.Part file_in
    );

}
