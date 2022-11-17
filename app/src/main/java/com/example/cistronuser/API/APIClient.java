package com.example.cistronuser.API;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

   // private static final String LOGINURL = "http://192.168.29.157/beta1/app/";
  private static final String LOGINURL = "http://192.168.29.173/beta1/app/";
  //private static final String LOGINURL = "https://cistronsystems.in/beta1/app/";
    private static Retrofit retrofit;

    public static Retrofit getClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        retrofit = new Retrofit.Builder()
                .baseUrl(LOGINURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();


        return retrofit;

    }

}
