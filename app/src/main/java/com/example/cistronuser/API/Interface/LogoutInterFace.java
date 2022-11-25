package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.LogoutResponse;
import com.example.cistronuser.API.Response.WaitingExpenseCountInterface;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LogoutInterFace {


    //user_action.php?action=logout&empid=e367

    @GET("user_action.php")
    Call<LogoutResponse> Calllogout(@Query("action")String action,@Query("empid")String empid,@Query("ip")String ip);
}
