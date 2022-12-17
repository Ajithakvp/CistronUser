package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.SalesQuotesUpdateFinalizeResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface SalesQuotesUpdateFinalizeInteface {

    //sales_quote.php?action=finalSubmit&empid=e367&quoteId=2&po_ref=test po&po_date=2022-12-16&billing_opt=1
    // &billing=Test Hospital pvm
    //Hospital Road
    //Veppanthattai
    //Perambalur-621116
    //Tamil nadu.&delivery=Test Hospital pvm
    //Hospital Road
    //Veppanthattai
    //Perambalur-621116
    //Tamil nadu.&con_pre=Mr. &con_name=Test doctor&con_no=8876543210&pro_spec=As per Quotation&order_value=11000&advance_value=3000
    // &deposit=1&deposit_date=2022-12-17&pay_bef_dispatch=1000&pay_aft_dispatch=2000&pay_on_install=3000
    // &installments=500,1500&install_terms=Remarks for Payment&spl_remarks=Special Remarks&warranty=12&delivery_date=2022-12-18

    @Multipart
    @POST("sales_quote.php")
    Call<SalesQuotesUpdateFinalizeResponse>callFinalSubmit(@Part("action")RequestBody action,
                                                           @Part("empid")RequestBody empid,
                                                           @Part("quoteId")RequestBody quoteId,
                                                           @Part("po_ref")RequestBody po_ref,
                                                           @Part("po_date")RequestBody po_date,
                                                           @Part("billing_opt")RequestBody billing_opt,
                                                           @Part("billing")RequestBody billing,
                                                           @Part("delivery")RequestBody delivery,
                                                           @Part("con_pre")RequestBody con_pre,
                                                           @Part("con_name")RequestBody con_name,
                                                           @Part("con_no")RequestBody con_no,
                                                           @Part("pro_spec")RequestBody pro_spec,
                                                           @Part("order_value")RequestBody order_value,
                                                           @Part("advance_value")RequestBody advance_value,
                                                           @Part("deposit")RequestBody deposit,
                                                           @Part("deposit_date")RequestBody deposit_date,
                                                           @Part("pay_bef_dispatch")RequestBody pay_bef_dispatch,
                                                           @Part("pay_aft_dispatch")RequestBody pay_aft_dispatch,
                                                           @Part("pay_on_install")RequestBody pay_on_install,
                                                           @Part("installments")RequestBody installments,
                                                           @Part("install_terms")RequestBody install_terms,
                                                           @Part("spl_remarks")RequestBody spl_remarks,
                                                           @Part("warranty")RequestBody warranty,
                                                           @Part("delivery_date")RequestBody delivery_date,
                                                           @Part MultipartBody.Part poPdf);

}
