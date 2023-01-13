package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.SpareSendReqListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SpareSendReqListInterface {

    //http://192.168.29.173/beta1/app/service_engineers.php?action=spareRequestTmp&empid=tempyugan

    @GET("service_engineers.php")
    Call<SpareSendReqListResponse>CallSpareList(@Query("action")String action,@Query("empid")String empid);
}
