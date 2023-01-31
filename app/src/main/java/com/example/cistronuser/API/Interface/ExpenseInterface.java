package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.ExpenseResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ExpenseInterface {

//    #API:expenses.php?action=saveExpenses&empid=e367&date=2022-11-14&empid=e367&
//    c_amo=100&t_amo=200&l_amo=300&o_amo=400&active=0&
//    startdate=2022-11-13&enddate=2022-11-19&workreport=
//    #File attachment: filename_all=&filename_t=&filename_l=&filename_o=


    @Multipart
    @POST("expenses.php")
    Call<ExpenseResponse> CallExpenseSubmit(@Part("action") RequestBody action,
                                            @Part("empid") RequestBody empid,
                                            @Part("c_amo") RequestBody c_amo,
                                            @Part("t_amo") RequestBody t_amo,
                                            @Part("l_amo") RequestBody l_amo,
                                            @Part("o_amo") RequestBody o_amo,
                                            @Part("active") RequestBody active,
                                            @Part("startdate") RequestBody startdate,
                                            @Part("enddate") RequestBody enddate,
                                            @Part("date") RequestBody date,
                                            @Part("workreport") RequestBody workreport

    );


    @Multipart
    @POST("expenses.php")
    Call<ExpenseResponse> CallConvency(@Part("action") RequestBody action,
                                       @Part("empid") RequestBody empid,
                                       @Part("date") RequestBody date,
                                       @Part MultipartBody.Part filename_all

    );


    @Multipart
    @POST("expenses.php")
    Call<ExpenseResponse> CallTicket(@Part("action") RequestBody action,
                                     @Part("empid") RequestBody empid,
                                     @Part("date") RequestBody date,
                                     @Part MultipartBody.Part filename_t

    );

    @Multipart
    @POST("expenses.php")
    Call<ExpenseResponse> CallLodging(@Part("action") RequestBody action,
                                      @Part("empid") RequestBody empid,
                                      @Part("date") RequestBody date,
                                      @Part MultipartBody.Part filename_l

    );


    @Multipart
    @POST("expenses.php")
    Call<ExpenseResponse> CallOther(@Part("action") RequestBody action,
                                    @Part("empid") RequestBody empid,
                                    @Part("date") RequestBody date,
                                    @Part MultipartBody.Part filename_o

    );


    @Multipart
    @POST("expenses.php")
    Call<ExpenseResponse> Callsubmit(@Part("action") RequestBody action,
                                     @Part("empid") RequestBody empid,
                                     @Part("date") RequestBody date,
                                     @Part MultipartBody.Part filename_r

    );


}
