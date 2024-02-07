package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.FeedbackFileUploadResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface FeedbackFileUploadInterface {
    @Multipart
    @POST("feedbackreport_app")
    Call<FeedbackFileUploadResponse> call(@Part("action")RequestBody action,
                                          @Part("hid")RequestBody hid,
                                          @Part("pid")RequestBody pid,
                                          @Part("sid")RequestBody sid,
                                          @Part("user")RequestBody user,
                                          @Part("cid")RequestBody cid,
                                          @Part("date")RequestBody date,
                                          @Part MultipartBody.Part fileName);
}
