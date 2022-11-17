package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.SelectWeekResponse;
import com.example.cistronuser.API.Response.ViewExpenseResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ViewExpenseListInterface {

    //expenses.php?action=viewWeeklyExpenses&date=2022-11-10&empid=e367

    @GET("expenses.php")
    Call<ViewExpenseResponse> CallSelectWeek(@Query("action")String action, @Query("date")String date, @Query("empid")String empid);
}
