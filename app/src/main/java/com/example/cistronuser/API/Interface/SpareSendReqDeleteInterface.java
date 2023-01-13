package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.SpareSendReqDeleteRespones;
import com.example.cistronuser.API.Response.SpareSendReqListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SpareSendReqDeleteInterface {

    //http://192.168.29.173/beta1/app/service_engineers.php?action=deleteSpareRequestTmp&id=1


    @GET("service_engineers.php")
    Call<SpareSendReqDeleteRespones> CallSpareListDelete(@Query("action")String action, @Query("id")String id);
}
