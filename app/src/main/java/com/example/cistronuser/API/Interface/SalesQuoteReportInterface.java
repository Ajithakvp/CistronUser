package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.SalesQuoteReportResponse;
import com.example.cistronuser.API.Response.SalesQuoteSubmitedResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SalesQuoteReportInterface {

    //http://192.168.29.173/beta1/app/sales_quote.php?action=getQuoteReport&from_date=2022-12-01&to_date=2022-12-31&empid=0&p1=

    @GET("sales_quote.php")
    Call<SalesQuoteReportResponse> CallQuoteReport(@Query("action")String action, @Query("from_date")String from_date,
                                                    @Query("to_date")String to_date, @Query("empid")String empid,
                                                    @Query("p1")String p1);
}
