package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.SalesQuoteSubmitedResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SalesQuoteSubmitedInterface {

    //http://192.168.29.173/beta1/app/sales_quote.php?action=generateSalesQuote&empid=e367&hospital=15165&doctor=0
    // &catId=1&prodId=457&price=575000&mailTo=callmeasvelan@gmail.com&mailCC=vallivelprof@gmail.com&sms=1
    // &mobile=8110982923&addons=644,565&latitude=123&longitude=456&ipaddress=1.2.3.4&place=Trichy


    @GET("sales_quote.php")
    Call<SalesQuoteSubmitedResponse> salesQuoteSubmit(@Query("action")String action,@Query("empid")String empid,
                                                      @Query("hospital")String hospital,@Query("doctor")String doctor,
                                                      @Query("catId")String catId,@Query("prodId")String prodId,
                                                      @Query("price")String price,@Query("mailTo")String mailTo,
                                                      @Query("mailCC")String mailCC, @Query("sms")int sms,
                                                      @Query("mobile")String mobile, @Query("addons")String addons,
                                                      @Query("latitude")double latitude, @Query("longitude")double longitude,
                                                      @Query("ipaddress")String ipaddress, @Query("place")String place);
}
