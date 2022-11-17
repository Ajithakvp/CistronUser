package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.AttachRemoveResponse;
import com.example.cistronuser.API.Response.ExpensePolicyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ExpensePolicyInterface {

    // expenses.php?action=checkExpPolicy&empid=e367

    @GET("expenses.php")
    Call<ExpensePolicyResponse> CallPolicyExpenses(@Query("action")String action, @Query("empid")String empid);

}
