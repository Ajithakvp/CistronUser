package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Model.CreateSendSpareReqSubmitResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CreateSendSpareReqSubmitInterface {

    // http://192.168.29.173/beta1/app/service_engineers.php?action=submitSpareRequestsQueue&
    // empid=e350&tmp_partids[]=2774&tmp_qty[]=1&tmp_seriesid[]=1&tmp_purpose[]=Stock

    @GET("service_engineers.php")
    Call<CreateSendSpareReqSubmitResponse>CallSubmit(@Query("action")String action,
                                                     @Query("empid")String empid,
                                                     @Query("tmp_partids[]") ArrayList<String> tmp_partids,
                                                     @Query("tmp_qty[]") ArrayList<String> tmp_qty,
                                                     @Query("tmp_seriesid[]") ArrayList<String> tmp_seriesid,
                                                     @Query("tmp_purpose[]") ArrayList<String> tmp_purpose);
}
