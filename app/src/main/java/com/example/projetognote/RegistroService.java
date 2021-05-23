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
import retrofit2.http.Query;

public interface RegistroService {
    @POST("registro")
    Call<Registro> adicionarRegistro(@Body Registro registro);

    @POST("registro/mes/{mes}")
    Call<List<Registro>> buscarMes( @Path("mes") int mes);

//    @GET("registro?dia={dia}&mes={mes}&ano={ano}&id={id}")
//    Call<List<Registro>> buscarDia(@Query("dia") int dia, @Query("mes") int mes, @Query("ano") int ano, @Query("id") long id);

    @GET("registro")
    Call<List<Registro>> buscarDia(@Query("dia") int dia, @Query("mes") int mes, @Query("ano") int ano, @Query("id") long id);

    @POST("registro/usuario/{id}")
    Call<List<Registro>> buscarUsuario(@Path("id") long id );

    @GET("registro")
    Call<List<Registro>> listarRegistros();

    @PUT("registro/{id}")
    Call<Registro> atualizar(@Path("id") long id, @Body Registro registro);

    @DELETE("registro/{id}")
    Call<Void> deletar(@Path("id") long id);
}