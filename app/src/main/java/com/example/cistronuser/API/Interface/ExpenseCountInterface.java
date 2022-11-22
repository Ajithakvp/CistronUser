package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.LeaveDetailsResponse;
import com.example.cistronuser.API.Response.WaitingExpenseCountInterface;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ExpenseCountInterface {

    //expenses.php?action=expensesApprovalCount

    @GET("expenses.php")
    Call<WaitingExpenseCountInterface> Callcount(@Query("action")String action);
}
