package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.VisitEntryDeleteRessponse;
import com.example.cistronuser.API.Response.VisitEntryListSubmitResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VisitEntryListSubmitInterFace {

    //http://192.168.29.173/beta1/app/visit_entry.php?action=submitVisitEntries&empid=e367


    @GET("visit_entry.php")
    Call<VisitEntryListSubmitResponse> CallVisitEntrySubmit(@Query("action")String action, @Query("empid")String empid);
}
