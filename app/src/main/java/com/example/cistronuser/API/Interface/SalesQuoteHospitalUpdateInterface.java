package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.SalesQuoteHospitalUpdateResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SalesQuoteHospitalUpdateInterface {


    //http://192.168.29.173/beta1/app/sales_quote.php?action=viewAvailableSalesQuote&hospitalId=15165

    @GET("sales_quote.php")
    Call<SalesQuoteHospitalUpdateResponse>CallHospitalUpdateList(@Query("action")String action,@Query("hospitalId")String hospitalId);
}
