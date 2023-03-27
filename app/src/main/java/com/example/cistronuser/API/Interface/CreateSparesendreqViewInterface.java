package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.CreateSparesendreqViewResponse;
import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CreateSparesendreqViewInterface {

    // http://192.168.29.173/beta1/app/service_engineers.php?action=viewSpareRequestQueue&empid=e0003

    @GET("service_engineers.php")
    Call<CreateSparesendreqViewResponse>CallSpareReq(@Query("action")String action,@Query("empid")String empid);
}
