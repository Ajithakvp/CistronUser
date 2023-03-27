package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.DeleteSendSpareReqResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DeleteSendSpareReqInterface {

    // http://192.168.29.173/beta1/app/service_engineers.php?action=deleteFromSpareRequestsQueue&tmpId=

    @GET("service_engineers.php")
    Call<DeleteSendSpareReqResponse>CallDelete(@Query("action")String action,@Query("tmpId")String tmpId);
}
