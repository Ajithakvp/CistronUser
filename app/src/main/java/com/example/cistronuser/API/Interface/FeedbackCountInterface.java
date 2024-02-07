package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.FeedbackCountResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FeedbackCountInterface {
    // https://cistronsystems.in/beta1/app/feedbackreport_app.php?action=reportcount&user=test

    @GET("feedbackreport_app.php")
    Call<FeedbackCountResponse>callCount(@Query("action") String action,
                                         @Query("user") String user);
}
