package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Model.AttendanceMessageModel;
import com.example.cistronuser.API.Response.AttachRemoveResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AttachRemoveInterface {

//    expenses.php?action=removeAttachment&date=2022-11-10&empid=e367&attachType=
//    {filename_all,filename_t,filename_l,filename_o,filename_r}

    @GET("expenses.php")
    Call<AttachRemoveResponse> CallAttachremove(@Query("action")String action, @Query("date")String date, @Query("empid")String empid, @Query("attachType")String attachType);

}
