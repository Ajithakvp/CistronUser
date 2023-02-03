package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.ConsumeSpareSubmitResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ConsumeSpareSubmitInterface {
    //http://192.168.29.173/beta1/app/service_engineers.php?action=onConsumeSpares&sqid=953&
    // id=7522~12806~19141~dashboard~260~up&payopt=4&partid[]=&myQty[]=&consumeQty[]=&price[]=&opt[]=&empid=e350

    @GET("service_engineers.php")
    Call<ConsumeSpareSubmitResponse>CallSubmit(@Query("action")String action, @Query("sqid")String sqid,
                                               @Query("id")String id, @Query("payopt")String payopt,
                                               @Query("partid[]") ArrayList<String> partid, @Query("myQty[]") ArrayList<String> myQty,
                                               @Query("consumeQty[]") ArrayList<String> consumeQty, @Query("price[]") ArrayList<String> price,
                                               @Query("opt[]") ArrayList<String> opt, @Query("empid")String empid);
}
