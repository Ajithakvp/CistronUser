package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.ServiceSpareSendReqbtnResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceSpareSendReqbtnInterface {

    //http://192.168.29.173/beta1/app/service_engineers.php?action=addSpareRequestTmp&empId=tempyugan&p=2716&pcat=1&s=1

    @GET("service_engineers.php")
    Call<ServiceSpareSendReqbtnResponse> callSendReqSubmit(@Query("action")String action,@Query("empId")String empId,
                                                           @Query("p")String p,@Query("pcat")String pcat,
                                                           @Query("s")String s);
}
