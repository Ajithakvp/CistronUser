package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.SalesQuoteAddResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SalesQuoteAddInterface {

    //Preview

    //http://192.168.29.173/beta1/app/sales_quote.php?action=salesQuotePreview&catId=1&prodId=457&hospId=15165&addon=75000~644,125000~565

    @GET("sales_quote.php")
    Call<SalesQuoteAddResponse>CallPreview(@Query("action")String action,@Query("catId")String catId,
                                           @Query("prodId")String prodId,@Query("hospId")String hospId,
                                           @Query("addon")String addon);

}
