package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.YesDoYouConsumeSpareResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YesDoYouConsumeSpareInterface {

    //http://192.168.29.173/beta1/app/service_engineers.php?action=doYouConsumeSpares&
    // partid=15,1~508,1~771,1~829,1~835,1~859,1&payopt=4&id=7522~12806~19141~dashboard~260~up&sqid=988&_=1675402644347&empid=e350
    //http://192.168.29.173/beta1/app/service_engineers.php?action=doYouConsumeSpares&partid=15,1~508,1~771,1~829,1~835,1~859,1&
    // payopt=4&id=7522~12806~19141~dashboard~260~up&sqid=988&empid=e350

    @GET("service_engineers.php")
    Call<YesDoYouConsumeSpareResponse> CallSpare(@Query("action")String action, @Query("partid")String partid, @Query("payopt")String payopt, @Query("id")String id,@Query("sqid")String sqid,
                                                 @Query("empid")String empid);
}
