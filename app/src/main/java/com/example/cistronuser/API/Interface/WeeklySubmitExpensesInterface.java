package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.ExpenseResponse;
import com.example.cistronuser.API.Response.WeeklySubmitExpensesResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface WeeklySubmitExpensesInterface {

    //http://192.168.29.173/beta1/app/expenses.php?action=saveExpenses&empid=e367&startdate=2022-11-13&enddate=2022-11-19


    @Multipart
    @POST("expenses.php")
    Call<WeeklySubmitExpensesResponse> CallConvency(@Part("action") RequestBody action,
                                                    @Part("empid") RequestBody empid,
                                                    @Part("startdate")RequestBody startdate,
                                                    @Part("enddate")RequestBody enddate,
                                                    @Part MultipartBody.Part filename_r
    );
}
