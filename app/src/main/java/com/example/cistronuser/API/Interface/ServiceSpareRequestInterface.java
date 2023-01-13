package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.ServiceSpareRequestResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceSpareRequestInterface {


    //http://192.168.29.173/beta1/app/service_engineers.php?action=onRequireSparesCallStatus&seriesid1=2&seriesid2=

    @GET("service_engineers.php")
    Call<ServiceSpareRequestResponse> CallReq(@Query("action")String action,@Query("seriesid1")String seriesid1,
                                              @Query("seriesid2")String seriesid2);
}
