package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.AcceptSpareInwardResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AcceptSpareInwardInterFace {


    //http://192.168.29.173/beta1/app/service_engineers.php?action=acceptSparesInward&cdc_no=on&dc_no=dc101&cinv_no=on&
    // in_no=inv101&cpo_no=on&po_no=po101&ctransporttype=on&transporttype=2&
    // tr_ref=ref&cdated=on&dat=2023-01-15&time=08:10:12&id=2&empid=tempyugan&opt=1

    @GET("service_engineers.php")
    Call<AcceptSpareInwardResponse> CallAccept(@Query("action")String action,
                                               @Query("cdc_no")String cdc_no,
                                               @Query("dc_no")String dc_no,
                                               @Query("cinv_no")String cinv_no,
                                               @Query("in_no")String in_no,
                                               @Query("cpo_no")String cpo_no,
                                               @Query("po_no")String po_no,
                                               @Query("ctransporttype")String ctransporttype,
                                               @Query("transporttype")String transporttype,
                                               @Query("tr_ref")String tr_ref,
                                               @Query("cdated")String cdated,
                                               @Query("dat")String dat,
                                               @Query("time")String time,
                                               @Query("id")String id,
                                               @Query("empid")String empid,
                                               @Query("opt")String opt);
}
