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
import retrofit2.http.Query;

public interface UsuarioService {
    @POST("usuarios")
    Call<Usuario> adicionar(@Body Usuario usuario);

    @POST("usuarios/login")
    Call<Usuario> login(@Query("email") String email, @Query("senha") String senha);

    @GET("usuarios")
    Call<List<Usuario>> listarUsuario();

    @PUT("usuarios/{id}")
    Call<Usuario> atualizar(@Path("id") long id, @Body Usuario usuario);

    @DELETE("usuarios/{id}")
    Call<Void> deletar(@Path("id") long id);

}
