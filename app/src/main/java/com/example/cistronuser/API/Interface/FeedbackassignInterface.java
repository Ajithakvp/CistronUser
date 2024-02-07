package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.FeedbackassignResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FeedbackassignInterface {

    //https://cistronsystems.in/beta1/app/feedbackreport_app.php?action=reportview&user=test

    @GET("feedbackreport_app.php")
    Call<FeedbackassignResponse> callRes(@Query("action")String action,
                                         @Query("user")String user);
}
