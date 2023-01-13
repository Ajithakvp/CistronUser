package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.ServiceSpareResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceSpareListInterface {

    //http://192.168.29.173/beta1/app/service_engineers.php?action=getSparePartsList&seriesId=2&empid=e367

    @GET("service_engineers.php")
    Call<ServiceSpareResponse>CallSpareList(@Query("action")String action,@Query("seriesId")String seriesId,@Query("empid")String empid);
}
