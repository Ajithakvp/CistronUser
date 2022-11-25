package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.AdjustmentExpensesResponse;
import com.example.cistronuser.API.Response.ChangePasswordResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ChangePasswordInterface {

    //user_action.php?action=changePassword&empi=e367&passwd=abc123



    @GET("user_action.php")
    Call<ChangePasswordResponse> callchangepasswor(@Query("action")String action,
                                                   @Query("empid")String empid, @Query("pwd")String passwd);
}
