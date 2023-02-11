package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.CallReportComplaintSubCategoryResponse;
import com.example.cistronuser.API.Response.UpcomingCallReportResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CallReportComplaintSubCategoryInterface {

    //http://192.168.29.173/beta1/app/service_engineers.php?action=getCompliantSubcategory&complaintId=21

    @GET("service_engineers.php")
    Call<CallReportComplaintSubCategoryResponse>CallSubCat(@Query("action")String action, @Query("complaintId")String complaintId,@Query("subProblemId")String subProblemId);
}
