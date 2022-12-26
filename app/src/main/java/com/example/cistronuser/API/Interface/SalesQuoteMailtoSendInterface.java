package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.SalesQuoteMailtoSendResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SalesQuoteMailtoSendInterface {


//     "empid": "E367",
//             "id": 53177,
//             "quote_id": "8910",
//             "doctor": "Mr.  TEST DOCTOR TEST DOCTOR MD",
//             "doc_email": "callmeasvelan@gmail.com",
//             "product": "Instrument Washer Cum Disinfector",
//             "m_status": 1,
//             "mailto": "callmeasvelan@gmail.com",
//             "mailcc": "ajithmaxwell3096@gmail.com",
//             "bro_name": "932858435_INW01.pdf",
//             "a": 1

    @GET("mailsend.php")
    Call<SalesQuoteMailtoSendResponse> callSendMail(@Query("empid")String empid, @Query("id")String id,
                                                    @Query("quote_id")String quote_id, @Query("doctor")String doctor,
                                                    @Query("doc_email")String doc_email, @Query("product")String product,
                                                    @Query("m_status")String m_status, @Query("mailto")String mailto,
                                                    @Query("mailcc")String mailcc, @Query("bro_name")String bro_name,
                                                    @Query("a")String a);
}
