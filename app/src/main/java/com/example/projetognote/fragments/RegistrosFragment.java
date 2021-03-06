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
import androidx.navigation.Navigation;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrosFragment extends Fragment implements AdapterRegistros.OnRegistroListener {

    private TextView tvData;
    private RecyclerView recyclerView;
    private List<Registro> listaRegistros;

    private AdapterRegistros adapterRegistros;
    private Usuario usuario;
    private RegistroService registroService;

    private java.sql.Date dataReg;
    private int dia, mes, ano;

    private SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd");

    private Bundle bundleDadosRegistro;
    private Registro selecionado;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_registros, container,false);

        inicializaComponentes(v);

        // configurando a data com o calendar
        dataReg = java.sql.Date.valueOf(data.format(new Date(System.currentTimeMillis())));
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataReg);
        mes = cal.get(Calendar.MONTH);
        dia = cal.get(Calendar.DAY_OF_MONTH);
        ano = cal.get(Calendar.YEAR);

        tvData.setText(String.valueOf(dataReg));

        this.registroService.buscarDia(dia, mes+1, ano, usuario.getIdUsuario()).enqueue(new Callback<List<Registro>>() {
            @Override
            public void onResponse(Call<List<Registro>> call, Response<List<Registro>> response) {
                if(response.isSuccessful()){
                    System.out.println("teste response ok" + mes+1);
                    listaRegistros.clear();
                    listaRegistros.addAll(response.body());
                    if(listaRegistros.isEmpty()){
                        Toast.makeText(getActivity(), "N??o h?? registros na data de hoje ;(", Toast.LENGTH_LONG).show();
                    }
                    adapterRegistros.notifyDataSetChanged();
                } else{
                    Log.i("Teste", "socorro" + response.raw());
                    Toast.makeText(getContext(), "N??o foi poss??vel encontrar os registros", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<List<Registro>> call, Throwable t) {
                Log.e("TesteRequisicao", t.getMessage());
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }

    // clicar na lista
    @Override
    public void onRegistroClick(int position) {
        Toast.makeText(getContext(), "clicou na linha: " + position, Toast.LENGTH_SHORT).show();
        selecionado = listaRegistros.get(position);

        bundleDadosRegistro = new Bundle();
        bundleDadosRegistro.putSerializable("registro", selecionado);
        Navigation.findNavController(getView()).navigate(R.id.home_to_editar_registro, bundleDadosRegistro);

    }

    private void inicializaComponentes(View v){
        this.recyclerView = v.findViewById(R.id.rv_registros);
        this.tvData = v.findViewById(R.id.tv_data);
        this.registroService = RetrofitBuilder.buildRetrofit().create(RegistroService.class);
        this.usuario = LoginActivity.usuarioLogado;

        this.listaRegistros = new ArrayList<>();

        this.adapterRegistros = new AdapterRegistros(this.listaRegistros, this);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        this.recyclerView.setAdapter(this.adapterRegistros);

    }
}