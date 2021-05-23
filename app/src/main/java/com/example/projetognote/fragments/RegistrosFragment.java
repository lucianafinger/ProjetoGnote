package com.example.projetognote.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetognote.RegistroService;
import com.example.projetognote.RetrofitBuilder;
import com.example.projetognote.activity.LoginActivity;
import com.example.projetognote.adapter.AdapterRegistros;
import com.example.projetognote.R;
import com.example.projetognote.model.Registro;
import com.example.projetognote.model.Usuario;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrosFragment extends Fragment implements AdapterRegistros.OnRegistroListener {

    private TextView tvData;
    private RecyclerView recyclerView;
    private List<Registro> listaRegistros;

//    private java.sql.Date data_registro;
//    int day, month, year;

//    DateTimeFormatter hora = DateTimeFormatter.ofPattern ("HH:mm:ss");
//    SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd");

    private Usuario usuario;
    private Registro registro;
    private RegistroService registroService;
//    private java.sql.Date dataRegistro;
//    private int dia, mes, ano;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_registros, container,false);

        recyclerView = v.findViewById(R.id.rv_registros);
        tvData = v.findViewById(R.id.tv_data);
        registroService = RetrofitBuilder.buildRetrofit().create(RegistroService.class);

        usuario = LoginActivity.usuarioLogado;

//        dataRegistro = java.sql.Date.valueOf(data.format(new Date(System.currentTimeMillis())));
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(dataRegistro);
//        mes = Integer.valueOf(cal.get(Calendar.MONTH));
//        dia = Integer.valueOf(cal.get(Calendar.DAY_OF_MONTH));
//        ano = Integer.valueOf(cal.get(Calendar.YEAR));

        tvData.setText(String.valueOf(registro.getData_registro()));

        System.out.println("teste2");

        System.out.println(usuario.getIdUsuario());

//        registroService.buscarDia(dia, mes, ano, usuario.getIdUsuario()).enqueue(new Callback<List<Registro>>() {
//            @Override
//            public void onResponse(Call<List<Registro>> call, Response<List<Registro>> response) {
//                if(response.isSuccessful()){
//                    if(response.body().isEmpty()){
//                        Toast.makeText(getContext(), "Usuário não possui registros de hoje", Toast.LENGTH_LONG).show();
//                    } else{
//                        Toast.makeText(getContext(), "else", Toast.LENGTH_LONG).show();
//                        listaRegistros = response.body();
//                    }
//                }else{
//                    Toast.makeText(getContext(), "Não há registros na data de hoje...", Toast.LENGTH_LONG).show();
//                }
//            }
//            @Override
//            public void onFailure(Call<List<Registro>> call, Throwable t) {
//                Log.e("TesteRequisicao", t.getMessage());
//                // socorro
//                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });

        // configurar adapter
        AdapterRegistros adapterRegistros = new AdapterRegistros(listaRegistros);
        recyclerView.setAdapter(adapterRegistros);

        // configurar recyclerview
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this.getContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapterRegistros);

        return v;
    }

    @Override
    public void onRegistroClick(int position) {
    }

}