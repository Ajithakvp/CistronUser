package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.UpcomingCallListResponse;
import com.example.cistronuser.API.Response.UpcomingCallReportResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UpcomingCallReportInterface {

    //http://192.168.29.173/beta1/app/service_engineers.php?action=callReporting&id=13465~17328~24354~dashboard~90~up
    //http://192.168.29.173/beta1/app/service_engineers.php?action=callReporting&id=14900~19087~26287~dashboard~136~up&empId=tempyugan


    @GET("service_engineers.php")
    Call<UpcomingCallReportResponse> CallUpcomingCallReport(@Query("action")String action, @Query("id")String id,@Query("empId")String empId );


}
