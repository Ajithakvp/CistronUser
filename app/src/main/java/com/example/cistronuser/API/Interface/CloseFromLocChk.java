package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.CloseFromLocChkResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CloseFromLocChk {


    @GET("closefromcrntlocation.php")
    Call<CloseFromLocChkResponse>callverify(@Query("crid")String crid);
}
