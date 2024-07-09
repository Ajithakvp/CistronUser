package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.Verfiy_otp_policy_Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Verfiy_otp_policy_interface {

    //https://cistronsystems.in/beta1/app/verify_otp_mail_app.php?otp=29152&empid=test&policytitle=p_lop

    @GET("verify_otp_mail_app.php")
    Call<Verfiy_otp_policy_Response>verfotp(@Query("otp")String otp,
                                            @Query("empid")String empid,
                                            @Query("policytitle")String policytitle);
}
