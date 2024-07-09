package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.Policy_otp_send_Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Policy_otp_send_interface {

    //https://cistronsystems.in/beta1/app/otp_send_mail_app.php?mail=ajithmaxwell3096@gmail.com&empid=test&policytitle=p_lop

    @GET("otp_send_mail_app.php")
    Call<Policy_otp_send_Response>sendotp(@Query("mail")String mail,
                                          @Query("empid")String empid,
                                          @Query("policytitle")String policytitle);
}
