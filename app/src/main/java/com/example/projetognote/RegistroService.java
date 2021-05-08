package com.example.projetognote;

import com.example.projetognote.model.Registro;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RegistroService {
    @POST("registro")
    Call<Registro> adicionar(@Body Registro registro);

    @GET("registro")
    Call<List<Registro>> listar();

    @PUT("registro/{id}")
    Call<Registro> editar(@Path("id") Integer id, @Body Registro registro);

    @DELETE("registro/{id}")
    Call<Void> deletar(@Path("id") Integer id);
}
