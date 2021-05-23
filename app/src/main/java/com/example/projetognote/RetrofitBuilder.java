package com.example.projetognote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalTime;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    public static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
//    public static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd")
//                                               .registerTypeAdapter(LocalTime.class, new LocalTimeSerializer())
//                                               .create();

    public static Retrofit buildRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://192.168.0.10:8090/")
//                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

}