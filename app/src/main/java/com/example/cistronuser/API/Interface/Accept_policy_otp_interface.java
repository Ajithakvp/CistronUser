package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.Accept_policy_otp_Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Accept_policy_otp_interface {

    // https://cistronsystems.in/beta1/app/accept_policy_app.php?
    // ipaddress=ipaddrss&empid=test&policytitle=p_lop&state=state&country_code=country_code&city=city&country=country&pincode=pincode

    @GET("accept_policy_app.php")
    Call<Accept_policy_otp_Response>accptotp(@Query("ipaddress")String ipaddress,
                                             @Query("empid")String empid,
                                             @Query("policytitle")String policytitle,
                                             @Query("state")String state,
                                             @Query("country_code")String country_code,
                                             @Query("city")String city,
                                             @Query("country")String country,
                                             @Query("pincode")String pincode);


}
