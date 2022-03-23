package com.cursoandroid.projetochecklistandroid.retrofit.config;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    public static Retrofit RETROFIT = null;

    public static Retrofit getRetrofit() {
        if (RETROFIT == null) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

            OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(
                    httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build();

            RETROFIT = new Retrofit.Builder()
                    .baseUrl("http://192.168.0.54:8080/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return RETROFIT;
    }
}
