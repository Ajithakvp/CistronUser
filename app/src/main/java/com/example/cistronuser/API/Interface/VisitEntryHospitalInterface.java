package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.VisitEntryGetDistrictResponse;
import com.example.cistronuser.API.Response.VisitEntryModelResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VisitEntryHospitalInterface {

    ////192.168.29.173/beta1/app/visit_entry.php?action=getStateHospitals&state=Tamil Nadu&district=Perambalur


    @GET("visit_entry.php")
    Call<VisitEntryModelResponse> CallHospital(@Query("action")String action, @Query("state")String state,@Query("district")String district);
}
