package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.SalesQuotegetHospitalAddressResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SalesQuotegetHospitalAddressInterface {

    //http://192.168.29.173/beta1/app/sales_quote.php?action=getHospitalAddress&hospitalId=5830
    @GET("sales_quote.php")
    Call<SalesQuotegetHospitalAddressResponse> CallAddrss(@Query("action")String action,@Query("hospitalId")String hospitalId);
}
