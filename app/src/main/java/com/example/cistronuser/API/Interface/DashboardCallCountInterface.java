package com.example.cistronuser.API.Interface;
import com.example.cistronuser.API.Response.DashboardCallCountResponse;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DashboardCallCountInterface {

   // service_engineers.php?action=getDashboardCounts&empid=e367

    @GET("service_engineers.php")
    Call<DashboardCallCountResponse>CallCount(@Query("action")String action, @Query("empid")String empid);
}
