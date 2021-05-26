package com.example.projetognote.fragments;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.projetognote.R;
import com.example.projetognote.RegistroService;
import com.example.projetognote.RetrofitBuilder;
import com.example.projetognote.activity.LoginActivity;
import com.example.projetognote.model.Registro;
import com.example.projetognote.model.Usuario;
import com.google.android.material.navigation.NavigationView;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MensalFragment extends Fragment {

    private ListView lvRegistroMeses;
    public List<Registro> registroList;
    public ArrayAdapter<Registro> registroArrayAdapter;
    private Spinner spMeses;
    private String[] vetMeses = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
    private RegistroService registroService;
    private int mes, ano;
    private Usuario usuario;
    private Bundle bundleDadosRegistro;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_mensal, container, false);

        this.inicializaComponentes(v);
        this.registroService = RetrofitBuilder.buildRetrofit().create(RegistroService.class);

        // configurando a data com o calendar
        SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date dataRegistro = java.sql.Date.valueOf(data.format( new Date(System.currentTimeMillis())));
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataRegistro);
        ano = cal.get(Calendar.YEAR);

        // adapter spinner mes
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,vetMeses);
        this.spMeses.setAdapter(adapter);

        this.spMeses.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mes = position+1;
                registroService.buscarMes(mes, ano, usuario.getIdUsuario()).enqueue(new Callback<List<Registro>>() {
                    @Override
                    public void onResponse(Call<List<Registro>> call, Response<List<Registro>> response) {
                        if (response.isSuccessful()){
                            if(response.body().isEmpty()){
                                Toast.makeText(getContext(), "Não há dados nesse mês!!", Toast.LENGTH_LONG).show();
                            }else {
                                registroList = response.body();
                                registroArrayAdapter = new ArrayAdapter<Registro>(getContext(), android.R.layout.simple_list_item_1, registroList);
                                lvRegistroMeses.setAdapter(registroArrayAdapter);
                            }
                        }else{
                            Toast.makeText(getContext(), "Erro ao listar", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Registro>> call, Throwable t) {
                        Toast.makeText(getContext(), "Erro ao listar - onFailure", Toast.LENGTH_LONG).show();
                    }
                });
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        this.lvRegistroMeses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Registro selecionado = registroList.get(position);

                bundleDadosRegistro = new Bundle();
                bundleDadosRegistro.putSerializable("registro", selecionado);
                Navigation.findNavController(v).navigate(R.id.mensal_to_editar_registro, bundleDadosRegistro);

            }
        });

        return v;
    }

    private void inicializaComponentes(View v){
        this.lvRegistroMeses = v.findViewById(R.id.lv_registro_mes);
        this.spMeses = v.findViewById(R.id.sp_meses);
        this.usuario = LoginActivity.usuarioLogado;
    }
}