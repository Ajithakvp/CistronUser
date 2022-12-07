package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.VisitEntryDoctorResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VisitEntryDoctorInterface {

    //http://192.168.29.173/beta1/app/visit_entry.php?action=getChiefDr&hospital_id=1514
    @GET("visit_entry.php")
    Call<VisitEntryDoctorResponse> callChefDoctor(@Query("action")String action,@Query("hospital_id")String hospital_id);
}
