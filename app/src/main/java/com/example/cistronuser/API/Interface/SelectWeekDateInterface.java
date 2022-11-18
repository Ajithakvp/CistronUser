package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.SelectWeekResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SelectWeekDateInterface {

    //expenses.php?action=viewWeeklyExpenses&date=2022-11-10

    @GET("expenses.php")
    Call<SelectWeekResponse> CallSelectWeek(@Query("action")String action,@Query("date")String date,@Query("empid")String empid);

}
