package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.SwapinwardAcceptResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SwapinwardAcceptInterface {

    ////// https://erp.cistronsystems.in/swapbyengaccept_app.php?
    // id=178&opt=1&ctransporttype=1&transporttype=3&tr_ref=testref&cdated=1&dated=2024-04-18%2011:49:50

    @GET("swapbyengaccept_app.php")
    Call<SwapinwardAcceptResponse>accept(@Query("id")String id,@Query("opt")String opt,
                                         @Query("ctransporttype")String ctransporttype,@Query("transporttype")String transporttype,
                                         @Query("tr_ref")String tr_ref,@Query("cdated")String cdated,
                                         @Query("dated")String dated);
}
