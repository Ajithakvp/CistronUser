package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.CallReportingUpComingSubmitResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CallReportingUpComingSubmitInterface {

    //http://192.168.29.173/beta1/app/service_engineers.php?action=callclose&callregid=19087&callassignid=26287&
    // lid=1241&pdtidd=136&hpidd=14900&pay_option=6&ccategory=1&csubcategory=1&others&sothers&call_type_details
    // &calltyp&place&call_list&chk1&call_status&ba_dispatch&ba_dispatchr&ins_date&bp_install&
    // bp_installr&balinspayamt&cf&cft&code&spc&follow_date&start&end&pend_reason&contact_person&contact_number&pr&
    // dated&tr_ref&service_cost&spare_cost&wd&ea&rate&work1&ser_reason&otp_name&otp_des&otp_mobile&otp


    //balinspay, call_status, callassignid, callregid, ccategory, cft, csubcategory, dated, ea, hpidd, ins_date, instamt, instamtr,
    // lid, nopay, others, pay_option, pdtidd, pr, rate, service_cost, sothers, spare_cost, spc, sqid, tm, tr_ref, wd,  ps,sq,wb,lr

    @GET("callClose.php")
    Call<CallReportingUpComingSubmitResponse> callReportSubmit(@Query("action")String action,
                                                               @Query("empid")String empid,
                                                               @Query("urlid")String urlid,
                                                               @Query("lid")String lid,
                                                               @Query("pay_option")String pay_option,
                                                               @Query("ccategory")String ccategory,
                                                               @Query("csubcategory")String csubcategory,
                                                               @Query("others")String others,
                                                               @Query("sothers")String sothers,
                                                               @Query("place")String place,
                                                               @Query("call_list")String call_list,
                                                               @Query("callregid")String callregid,
                                                               @Query("callassignid")String callassignid,
                                                               @Query("pdtidd")String pdtidd,
                                                               @Query("hpidd")String hpidd,
                                                               @Query("chk1")String chk1,
                                                               @Query("sqid")String sqid,
                                                               @Query("call_status")String call_status,
                                                               @Query("ba_dispatch")String ba_dispatch,
                                                               @Query("ba_dispatchr")String ba_dispatchr,
                                                               @Query("ins_date")String ins_date,
                                                               @Query("bp_install") String bp_install,
                                                               @Query("bp_installr") String bp_installr,
                                                               @Query("instamt") ArrayList<String> instamt,
                                                               @Query("instamtr") ArrayList<String> instamtr,
                                                               @Query("balinspay")String balinspayamt,
                                                               @Query("cft")String cft,
                                                               @Query("code")String code,
                                                               @Query("spc")String spc,
                                                               @Query("follow_date")String follow_date,
                                                               @Query("start")String start,
                                                               @Query("end")String end,
                                                               @Query("pend_reason")String pend_reason,
                                                               @Query("contact_person")String contact_person,
                                                               @Query("contact_number")String contact_number,
                                                               @Query("pr")String pr,
                                                               @Query("dated")String dated,
                                                               @Query("tr_ref")String tr_ref,
                                                               @Query("nopay")String  nopay,
                                                               @Query("service_cost")String service_cost,
                                                               @Query("spare_cost")String spare_cost,
                                                               @Query("wd")String wd,
                                                               @Query("ea")String ea,
                                                               @Query("rate")String rate,
                                                               @Query("work1")String work1,
                                                               @Query("ser_reason")String ser_reason,
                                                               @Query("ps")String ps,
                                                               @Query("sq")String sq,
                                                               @Query("wb")String wb,
                                                               @Query("lr")String lr,
                                                               @Query("sr")String sr,
                                                               @Query("pm")String pm,
                                                               @Query("lat") Double lat,
                                                               @Query("lng") Double lng,
                                                               @Query("address") String address,
                                                               @Query("homelat") Double homelat,
                                                               @Query("homelng") Double homelng);



}
