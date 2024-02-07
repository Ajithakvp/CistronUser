package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.FeedbackFiledownloadResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FeedbackFiledownloadInterface {
    //https://cistronsystems.in/beta1/app/feedbackreport_app.php?action=filedownload&uid=test&cid=7&pid=325

    @GET("feedbackreport_app")
    Call<FeedbackFiledownloadResponse> Callres(@Query("action")String action,
                                               @Query("uid")String uid,
                                               @Query("cid")String cid,
                                               @Query("pid")String pid);
}
