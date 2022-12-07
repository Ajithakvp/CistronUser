package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.VisitEntryDeleteRessponse;
import com.example.cistronuser.API.Response.VisitEntryListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VisitEntryDeleteInterface {

    //http://192.168.29.173/beta1/app/visit_entry.php?action=deleteVisitEntry&visit_entry=123

    @GET("visit_entry.php")
    Call<VisitEntryDeleteRessponse> CallVisitEntryDelete(@Query("action")String action, @Query("visit_entry")String visit_entry);
}
