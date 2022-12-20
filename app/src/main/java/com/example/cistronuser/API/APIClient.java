package com.example.cistronuser.API;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    //Localhost
    private static final String LOGINURL = "http://192.168.29.173/beta1/app/";
    //Testing
    //private static final String LOGINURL = "https://cistronsystems.in/beta1/app_demodp/";
    //App
    // private static final String LOGINURL = "https://cistronsystems.in/beta1/app/";
    private static Retrofit retrofit;

    public static Retrofit getClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        // OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(15, TimeUnit.MINUTES).readTimeout(5, TimeUnit.MINUTES).addInterceptor(interceptor).build();

//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .connectTimeout(1, TimeUnit.MINUTES)
//                .readTimeout(5, TimeUnit.MINUTES)
//                .writeTimeout(5, TimeUnit.MINUTES )
//                .addInterceptor(interceptor).build();


        retrofit = new Retrofit.Builder()
                .baseUrl(LOGINURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();


        return retrofit;

    }

}
