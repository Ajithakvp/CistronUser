package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.VisitEntryListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VisitEntryListInterface {


    //http://192.168.29.173/beta1/app/visit_entry.php?action=viewVisitEntries&empid=e367

    @GET("visit_entry.php")
    Call<VisitEntryListResponse> CallVisitEntryList(@Query("action")String action,@Query("empid")String empid);
}
