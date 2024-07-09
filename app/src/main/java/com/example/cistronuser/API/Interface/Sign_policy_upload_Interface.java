package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.Sign_policy_upload_Response;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Sign_policy_upload_Interface {

    //https://cistronsystems.in/beta1/app/policy_sign_upload.php?filename=1.png&empid=test&policytitle=p_lop
    @Multipart
    @POST("policy_sign_upload.php")
    Call<Sign_policy_upload_Response>upload(@Part("empid") RequestBody empid,
                                            @Part("policytitle") RequestBody policytitle,
                                            @Part MultipartBody.Part filename);
}
