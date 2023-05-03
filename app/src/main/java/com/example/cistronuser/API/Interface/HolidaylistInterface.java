package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.HolidaylistResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HolidaylistInterface {

    // https://cistronsystems.in/beta1/app/holidaylist.php

    @GET("holidaylist.php")
    Call<HolidaylistResponse> callReport();

}
