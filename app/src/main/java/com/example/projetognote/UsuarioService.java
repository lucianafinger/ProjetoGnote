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
    @POST("usuarios")
    Call<Usuario> adicionar(@Body Usuario usuario);

    @GET("/loginsenha/{email}/{senha}")
    Call<Usuario> loginsenha(@Path("email") String email, @Path("senha") String senha);

    @POST("usuarios/login/{email}/{senha}")
    Call<Usuario> login(@Path("email") String email, @Path("senha") String senha);
//    @POST("usuario/login")
//    Call<Usuario> login(@RequestParam String email, @RequestParam String senha);

    @GET("usuarios")
    Call<List<Usuario>> listarUsuario();

    @PUT("usuarios/{id}")
    Call<Usuario> atualizar(@Path("id") long id, @Body Usuario usuario);

    @DELETE("usuarios/{id}")
    Call<Void> deletar(@Path("id") long id);

}
