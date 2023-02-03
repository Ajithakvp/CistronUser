package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.ConsumerCusSpareSubmitResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ConsumeCusSpareSubmitInterface {

    //http://192.168.29.173/beta1/app/service_engineers.php?action=onConsumeCustSpares&id=7522~12806~19141~dashboard~260~up&sqid=953&
    // partId[]=&myQty[]=&qtyToConsume[]=&opt[]=&customerStockId[]=

    @GET("service_engineers.php")
    Call<ConsumerCusSpareSubmitResponse>CallSubmit(@Query("action")String action, @Query("id")String id,
                                                   @Query("sqid")String sqid, @Query("partId[]") ArrayList<String> partId,
                                                   @Query("myQty[]") ArrayList<String> myQty, @Query("qtyToConsume[]") ArrayList<String> qtyToConsume,
                                                   @Query("opt[]") ArrayList<String> opt, @Query("customerStockId[]") ArrayList<String> customerStockId,
                                                   @Query("empid")String empid);
}
