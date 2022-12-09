package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.VisitEnteyReportManagerListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VisitEnteyReportManagerListInterface {

    //http://192.168.29.173/beta1/app/visit_entry.php?action=getManagersFilter

    @GET("visit_entry.php")
    Call<VisitEnteyReportManagerListResponse>CallManagerList(@Query("action")String action);
}
