package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.VisitEnteyReportManagerListResponse;
import com.example.cistronuser.API.Response.VisitEntryReportUserIListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VisitEntryReportUserIListnterface {


    //http://192.168.29.173/beta1/app/visit_entry.php?action=getEmpWithVisitEntry&from=2022-12-07&to=2022-12-07

    @GET("visit_entry.php")
    Call<VisitEntryReportUserIListResponse> CallUserList(@Query("action")String action,@Query("from")String from,@Query("to")String to);

}
