package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.VisitEntryAddResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VisitEntryAddInterface {

    //http://192.168.29.173/beta1/app/visit_entry.php?action=addVisitEntry
    // &hospital=15154&doctor=0&product=8&comment=comment&ipaddress=&date=&empid=e367

    @GET("visit_entry.php")
    Call<VisitEntryAddResponse> CallAddVisitEntry(@Query("action")String action,
                                                  @Query("hospital")String hospital,
                                                  @Query("doctor")String doctor,
                                                  @Query("product")String product,
                                                  @Query("comment")String comment,
                                                  @Query("ipaddress")String ipaddress,
                                                  @Query("date")String date,
                                                  @Query("empid")String empid,
                                                  @Query("lat") Double lat,
                                                  @Query("lng") Double lng,
                                                  @Query("address") String address,
                                                  @Query("state") String  state,
                                                  @Query("city") String city,
                                                  @Query("countrycode") String countrycode,
                                                  @Query("pincode") String pincode);
}
