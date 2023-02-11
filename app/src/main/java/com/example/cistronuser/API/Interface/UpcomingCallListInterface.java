package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.UpcomingCallListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UpcomingCallListInterface {

    //service_engineers.php?action=getUpcomingCallsRecord&empid=tempyugan

    @GET("service_engineers.php")
    Call<UpcomingCallListResponse> CallUpcomingCallReport(@Query("action")String action, @Query("empid")String empid, @Query("category")String category);
}
