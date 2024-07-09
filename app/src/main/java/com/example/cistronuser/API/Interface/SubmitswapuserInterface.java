package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.SubmitswapuserResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SubmitswapuserInterface {
    // https://erp.cistronsystems.in/spares_list_eng3swap_app.php?
    // tmp_opt=1&transporttype2=3&tmp_ids2=3897&tmp_qty2=1&tmp_return2=1001&dated2=2024-04-17%2010:43:58
    // &user_list=69&tr_ref2=test&user1=test

    @GET("spares_list_eng3swap_app")
    Call<SubmitswapuserResponse> submit(@Query("tmp_opt")String tmp_opt,@Query("transporttype2")String transporttype2,
                                        @Query("tmp_ids2")String tmp_ids2,@Query("tmp_qty2")String tmp_qty2,
                                        @Query("tmp_return2")String tmp_return2,@Query("dated2")String dated2,
                                        @Query("user_list")String user_list,@Query("tr_ref2")String tr_ref2,
                                        @Query("user1")String user1);

}
