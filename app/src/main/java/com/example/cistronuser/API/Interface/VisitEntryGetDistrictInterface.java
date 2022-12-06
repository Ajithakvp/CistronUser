package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.VisitEntryGetDistrictResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VisitEntryGetDistrictInterface {

    //192.168.29.173/beta1/app/visit_entry.php?action=getDistricts&state=Tamil Nadu

    @GET("visit_entry")
    Call<VisitEntryGetDistrictResponse>CallDistrict(@Query("action")String action,@Query("state")String state);
}
