package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.SalesQuoteUpdateStatusResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SalesQuoteUpdateStatusInterface {

    //http://192.168.29.173/beta1/app/sales_quote.php?action=updateStatus&quoteid=1
    // &updatedate=2022-12-13&quotestatus=No Requirement&remarks=&empid=e367

    @GET("sales_quote.php")
    Call<SalesQuoteUpdateStatusResponse> callUpdatestatus(@Query("action")String action,@Query("quoteid")String quoteid,
                                                          @Query("updatedate")String updatedate,@Query("quotestatus")String quotestatus,
                                                          @Query("remarks")String remarks, @Query("empid")String empid);
}
