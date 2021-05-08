package com.example.projetognote;

import com.example.projetognote.model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UsuarioService {
    @POST("usuario")
    Call<Usuario> adicionar(@Body Usuario usuario);

    @GET("usuario")
    Call<List<Usuario>> listar();

    @PUT("usuario/{id}")
    Call<Usuario> editar(@Path("id") Integer id, @Body Usuario usuario);

    @DELETE("usuario/{id}")
    Call<Void> deletar(@Path("id") Integer id);

}
