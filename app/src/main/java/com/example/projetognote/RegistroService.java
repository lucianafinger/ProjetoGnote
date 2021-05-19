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
    Call<Registro> adicionarRegistro(@Body Registro registro);

    @POST("registro/mes/{mes}")
    Call<List<Registro>> buscarMes( @Path("mes") int mes);

    @POST("registro/dia/{dia}/{mes}")
    Call<List<Registro>> buscarDia(@Path("dia")int dia, @Path("mes")int mes);

    @POST("registro/usuario/{id}")
    Call<List<Registro>> buscarUSuario(@Path("id") long id );

    @GET("registro")
    Call<List<Registro>> listarRegistros();

    @PUT("registro/{id}")
    Call<Registro> atualizar(@Path("id") long id, @Body Registro registro);

    @DELETE("registro/{id}")
    Call<Void> deletar(@Path("id") long id);
}