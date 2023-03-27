package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.CreateSpareSendReqResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CreateSpareSendReqInterface {
    // http://192.168.29.173/beta1/app/service_engineers.php?action=addToSpareRequestsQueue&empid=e0006&spareId=&opt=cis/cspl

    @GET("service_engineers.php")
    Call<CreateSpareSendReqResponse>CalSend(@Query("action")String action,
                                            @Query("empid")String empid,
                                            @Query("spareId")String spareId,
                                            @Query("opt")String opt);
}
