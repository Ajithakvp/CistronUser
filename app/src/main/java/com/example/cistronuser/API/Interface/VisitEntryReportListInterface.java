package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.VisitEntryReportListResponse;
import com.example.cistronuser.API.Response.VisitEntryReportUserIListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VisitEntryReportListInterface {

    //http://192.168.29.173/beta1/app/visit_entry.php?action=visitReportForAdmin&empid=all&from=2022-12-07&to=2022-12-07&user_category=all


    @GET("visit_entry.php")
    Call<VisitEntryReportListResponse> CallVisitEntryReportList(@Query("action")String action, @Query("empid")String empid,
                                                    @Query("from")String from, @Query("to")String to,
                                                    @Query("user_category")String user_category);

}
