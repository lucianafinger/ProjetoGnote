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

//    @POST("registro/mes")
//    Call<List<Registro>> buscarMes(@RequestParam int mes);

//    @POST("registro/dia")
//    Call<List<Registro>> buscarDia(@RequestParam int dia, @RequestParam int mes);

//    @POST("registro/usuario")
//    Call<List<Registro>> buscarUSuario(@RequestParam long id);

    @GET("registro")
    Call<List<Registro>> listarRegistros();

    @PUT("registro/{id}")
    Call<Registro> atualizar(@Path("id") long id, @Body Registro registro);

    @DELETE("registro/{id}")
    Call<Void> deletar(@Path("id") long id);
}
