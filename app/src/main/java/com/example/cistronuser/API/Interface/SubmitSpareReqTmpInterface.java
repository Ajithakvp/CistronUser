package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.SubmitSpareReqTmpResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SubmitSpareReqTmpInterface {
    //http://192.168.29.173/beta1/app/service_engineers.php?action=submitSpareRequestsTmp&empid=tempyugan&qtys=1,2,1&callNo=22060162

    @GET("service_engineers.php")
    Call<SubmitSpareReqTmpResponse> submitSpare(@Query("action")String action,@Query("empid")String empid,
                                                @Query("qtys")String qtys,@Query("callNo")String callNo);

}
