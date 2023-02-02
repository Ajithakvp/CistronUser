package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.CallReportServiceSubmitResponse;
import com.example.cistronuser.API.Response.CallReportSpareConsumedSubmitResponse;
import com.example.cistronuser.API.Response.CallReportSubmitCusPoRespones;
import com.example.cistronuser.API.Response.CallReportSubmitResponse;
import com.example.cistronuser.API.Response.CallReportsubmitCusInvoiceResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface CallReportSubmitInterface {
    //http://192.168.29.173/beta1/app/callClose.php?action=uploadCustomerInvoice&pay_option=4&callassignid=&fileName=file_in1

    //#API: callClose.php?action=uploadCustomerInvoice&pay_option=4&callassignid=&fileName=file_in1
    //
    //#API: callClose.php?action=uploadInvoice&sqid=&callassignid=&fileName=file_in4sc
    //
    //#API: callClose.php?action=uploadDCForSparesConsumed&spc>0&call_status=1&callassignid=&fileName=file_in,file_in2sc,file_in3sc
    //
    //#API: callClose.php?action=uploadInstallationImages&call_status&callassignid=&fileName=installImg1,installImg2...installImg5
    //
    //#API: callClose.php?action=uploadWayBill&call_status=1&pay_option=8&callassignid=&empid=&fileName=file_inwb
    //
    //#API: callClose.php?action=uploadLR&call_status=1&pay_option=8&callassignid=&empid=&fileName=file_inlr



    //*********** Customer Invoice **********//
    //#API: callClose.php?action=uploadCustomerInvoice&pay_option=4&callassignid=&fileName=file_in1
    @Multipart
    @POST("callClose.php")
    Call<CallReportsubmitCusInvoiceResponse> CallUploadCusInvoice(@Part("action") RequestBody action, @Part("pay_option") RequestBody pay_option,
                                                                  @Part("callassignid") RequestBody callassignid, @Part MultipartBody.Part file_in1);





    //*********** Customer PO Upload Invoice **********//
    //#API: callClose.php?action=uploadInvoice&sqid=&callassignid=&fileName=file_in4sc
    @Multipart
    @POST("callClose.php")
    Call<CallReportSubmitCusPoRespones> CallUploadInvoice(@Part("action") RequestBody action, @Part("sqid") RequestBody sqid,
                                                          @Part("callassignid") RequestBody callassignid, @Part MultipartBody.Part file_in4sc);



    //***********  Spare Consumed **********//
    //#API: callClose.php?action=uploadDCForSparesConsumed&spc>0&call_status=1&callassignid=&fileName=file_in,file_in2sc,file_in3sc
    @Multipart
    @POST("callClose.php")
    Call<CallReportSpareConsumedSubmitResponse> CallSpareConsumedFile(@Part("action") RequestBody action, @Part("spc") RequestBody spc,
                                                                      @Part("call_status") RequestBody call_status, @Part("callassignid") RequestBody callassignid,
                                                                      @Part MultipartBody.Part file_in, @Part MultipartBody.Part file_in2sc,
                                                                      @Part MultipartBody.Part file_in3sc);


    //***********  Installation **********//
    //#API: callClose.php?action=uploadInstallationImages&call_status&callassignid=&fileName=installImg1,installImg2...installImg5
    @Multipart
    @POST("callClose.php")
    Call<CallReportSubmitResponse> CallInstallationFile (@Part("action") RequestBody action,
                                                         @Part("call_status") RequestBody call_status,
                                                         @Part("callassignid") RequestBody callassignid,
                                                         @Part("empid") RequestBody empid,
                                                         @Part MultipartBody.Part installImg1,
                                                         @Part MultipartBody.Part installImg2,
                                                         @Part MultipartBody.Part installImg3,
                                                         @Part MultipartBody.Part installImg4,
                                                         @Part MultipartBody.Part installImg5);


    //*********** Way Bill **********//
    //#API: callClose.php?action=uploadWayBill&call_status=1&pay_option=8&callassignid=&empid=&fileName=file_inwb
    @Multipart
    @POST("callClose.php")
    Call<CallReportSubmitResponse> CallWaybill(@Part("action") RequestBody action, @Part("call_status") RequestBody call_status,
                                                     @Part("pay_option") RequestBody pay_option,  @Part("callassignid") RequestBody callassignid,
                                               @Part("empid") RequestBody empid,@Part MultipartBody.Part file_inwb);


    //*********** Report Attach **********//
    //#API: callClose.php?action=uploadLR&call_status=1&pay_option=8&callassignid=&empid=&fileName=file_inlr
    @Multipart
    @POST("callClose.php")
    Call<CallReportServiceSubmitResponse> CallReportFile(@Part("action") RequestBody action, @Part("call_status") RequestBody call_status,
                                                         @Part("pay_option") RequestBody pay_option, @Part("callassignid") RequestBody callassignid,
                                                         @Part("empid") RequestBody empid, @Part MultipartBody.Part file_inlr);



}
