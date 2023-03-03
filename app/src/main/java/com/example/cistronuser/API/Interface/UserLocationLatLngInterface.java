package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.UserLocationLatLngResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserLocationLatLngInterface {

    //https://cistronsystems.in/beta1/app/attendance_leave.php?action=getHomeLocation&empid=e378

    @GET("attendance_leave.php")
    Call<UserLocationLatLngResponse>CallLatLng(@Query("action")String action,@Query("empid")String empid);
}
