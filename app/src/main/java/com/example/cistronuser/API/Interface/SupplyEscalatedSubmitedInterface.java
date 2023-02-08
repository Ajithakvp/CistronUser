package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.SupplyEscalatedSubmitedResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SupplyEscalatedSubmitedInterface {

    //http://192.168.29.173/beta1/app/service_engineers.php?action=onSupplyCallEscalate&empid=tempyugan
    // &logisticsId=1241&baDispatch=3000&baDispatchr=2000

    @GET("service_engineers.php")
    Call<SupplyEscalatedSubmitedResponse> CallSubmit(@Query("action")String action,@Query("empid")String empid,
                                                     @Query("logisticsId")String logisticsId,@Query("baDispatch")String baDispatch,
                                                     @Query("baDispatchr")String baDispatchr);
}
