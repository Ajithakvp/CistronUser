package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.EscalatePaymentSubmitResponse;
import com.example.cistronuser.API.Response.SubmitSpareReqTmpResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EscalatePaymentSubmitInterface {


    //http://192.168.29.173/beta1/app/service_engineers.php?
    // action=escalatePayment&logisticsId=1243&bp_install=17700&bp_installr=0&callNo=23010504&empId=tempyugan

    @GET("service_engineers.php")
    Call<EscalatePaymentSubmitResponse> submitEscalate(@Query("action") String action, @Query("logisticsId") String logisticsId,
                                                       @Query("bp_install") String bp_install, @Query("bp_installr") String bp_installr,
                                                       @Query("callNo") String callNo, @Query("empId") String empId);

}
