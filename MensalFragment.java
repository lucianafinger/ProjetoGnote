package com.example.projetognote.fragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.projetognote.R;
import com.example.projetognote.RegistroService;
import com.example.projetognote.RetrofitBuilder;
import com.example.projetognote.activity.LoginActivity;
import com.example.projetognote.model.Registro;
import com.example.projetognote.model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MensalFragment extends Fragment {
    private RegistroService registroService;
    private Usuario usuario;
    private ListView lv_lista_mes;
    public List<Registro> listRegistro;
    public ArrayAdapter<Registro> registroArrayAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_mensal, container, false);

        registroService = RetrofitBuilder.buildRetrofit().create(RegistroService.class);
        usuario = LoginActivity.usuariologado;
        lv_lista_mes = v.findViewById(R.id.lv_lista_mes);

        registroService.buscarMes(5).enqueue(new Callback<List<Registro>>() {
            @Override
            public void onResponse(Call<List<Registro>> call, Response<List<Registro>> response) {
                if(response.isSuccessful()){
                listRegistro = response.body();
//                registroArrayAdapter = new   ArrayAdapter<Registro>( MensalFragment.this, android.R.layout.simple_list_item_1, listRegistro);
//                lv_lista_mes.setAdapter(registroArrayAdapter);
            }else{

            }
            }

            @Override
            public void onFailure(Call<List<Registro>> call, Throwable t) {

            }
        });

        return v;
    }
}