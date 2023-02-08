package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.InstallamentEscalatedSubmitResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InstallamentEscalatedSubmitInterface {

    //service_engineers.php?action=onInstallmentEscalate&empid=tempyugan&logisticsId=1241&
    // bp_installmentTot=6000&bp_installmentrTot=0&tm=1&installmentAMt[]=1000

    @GET("service_engineers.php")
    Call<InstallamentEscalatedSubmitResponse>CallSubmit(@Query("action")String action,@Query("empid")String empid,
                                                        @Query("logisticsId")String logisticsId,@Query("bp_installmentTot")String bp_installmentTot,
                                                        @Query("bp_installmentrTot")String bp_installmentrTot,@Query("tm")String tm,
                                                        @Query("installmentAMt[]") ArrayList<String> installmentAMt);
}
