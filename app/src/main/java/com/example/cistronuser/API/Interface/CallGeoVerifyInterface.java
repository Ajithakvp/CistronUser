package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.CallGeoVerifyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CallGeoVerifyInterface {

    //#API: verifygeo.php?action=verifygeo

    //$sql="insert into hospital_geocode_log(hospital_id,lat,lng,address,city,state,country_code,pincode,user,crid,action,reference)
    // values('{$hospId}', '{$lat}', '{$lng}', '{$address}', '{$city}',
    // '{$state}', '{$country_code}', '{$pincode}', '{$empid}','{$crid}' ,'call_close', '{$reference}', '{$timestamp}')"

    @GET("verifygeo.php")
    Call<CallGeoVerifyResponse> CallGeoverify(@Query("action")String action, @Query("hospital_id")String hospital_id,
                                              @Query("lat")Double lat, @Query("lng")Double lng,
                                              @Query("address")String address, @Query("city")String city,
                                              @Query("state")String state, @Query("countrycode")String country_code,
                                              @Query("pincode")String pincode, @Query("user")String user,
                                              @Query("crid")String crid, @Query("callassignid")String callassignid,
                                              @Query("distance")Double distance,@Query("updategeolocations") String updategeolocations);

}
