package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.VisityEntryStateResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VisitEntryStateInterface {

    //http://192.168.29.173/beta1/app/visit_entry.php?action=getUserStates&empid=e367

    @GET("visit_entry.php")
    Call<VisityEntryStateResponse> CallState(@Query("action")String action,@Query("empid")String empid);

}
