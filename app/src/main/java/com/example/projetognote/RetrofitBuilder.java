package com.example.projetognote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    public static Retrofit buildRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://192.168.0.10:8090/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
