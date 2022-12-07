package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.VisitEntryModelResponse;
import com.example.cistronuser.API.Response.VisitEntryProductResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VisitEntryProductInterface {
    //192.168.29.173/beta1/app/visit_entry.php?action=getAvailProd

    @GET("visit_entry.php")
    Call<VisitEntryProductResponse> CallProduct(@Query("action")String action);
}
