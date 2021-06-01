package com.example.projetognote.fragments;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetognote.R;
import com.example.projetognote.RegistroService;
import com.example.projetognote.RetrofitBuilder;
import com.example.projetognote.activity.LoginActivity;
import com.example.projetognote.adapter.AdapterMes;
import com.example.projetognote.adapter.AdapterRegistros;
import com.example.projetognote.model.Registro;
import com.example.projetognote.model.Usuario;
import com.google.android.material.navigation.NavigationView;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MensalFragment extends Fragment implements AdapterMes.OnRegistroListenerMes {

    private TextView tvBom, tvHipo, tvHiper, tvMuitoBom;
    private int countMuitoBom, countHipo, countHiper, countBom;

    private RecyclerView recyclerView;
    private List<Registro> listaRegistrosMes;

    private AdapterMes adapterMes;

    private Usuario usuario;
    private RegistroService registroService;

    private java.sql.Date dataReg;
    private SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd");
    private int mes, ano;

    //spinner meses
    public ArrayAdapter<Registro> registroArrayAdapter;
    private Spinner spMeses;
    private String[] vetMeses = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};

    private Bundle bundleDadosRegistro;
    private Registro selecionado;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_mensal, container, false);

        this.inicializaComponentes(v);

        // configurando a data com o calendar
        dataReg = java.sql.Date.valueOf(data.format(new Date(System.currentTimeMillis())));
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataReg);
        mes = cal.get(Calendar.MONTH);
        ano = cal.get(Calendar.YEAR);

        // adapter spinner mes
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, vetMeses);
        this.spMeses.setAdapter(adapter);

        this.spMeses.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mes = position + 1;
                registroService.buscarMes(mes, ano, usuario.getIdUsuario()).enqueue(new Callback<List<Registro>>() {
                    @Override
                    public void onResponse(Call<List<Registro>> call, Response<List<Registro>> response) {
                        if (response.isSuccessful()) {
                            listaRegistrosMes.clear();
                            listaRegistrosMes.addAll(response.body());

                            for(int i=0; i<listaRegistrosMes.size(); i++){
                                if (listaRegistrosMes.get(i).getEtiqueta().equals("2")) {
                                    countMuitoBom++;
                                } else if (listaRegistrosMes.get(i).getEtiqueta().equals("1")) {
                                    countHipo++;
                                } else if (listaRegistrosMes.get(i).getEtiqueta().equals("3")) {
                                    countHiper++;
                                } else {
                                    countBom++;
                                }
                            }

                            tvMuitoBom.setText(String.valueOf(countMuitoBom));
                            tvBom.setText(String.valueOf(countBom));
                            tvHiper.setText(String.valueOf(countHiper));
                            tvHipo.setText(String.valueOf(countHipo));

                            if (listaRegistrosMes.isEmpty()) {
                                Toast.makeText(getActivity(), "Não há registros neste mês", Toast.LENGTH_LONG).show();
                            }
                            adapterMes.notifyDataSetChanged();
                        } else {
                            Log.i("Teste", "socorro" + response.raw());
                            Toast.makeText(getContext(), "Não foram encontrados registros neste mês", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Registro>> call, Throwable t) {
                        Log.e("TesteRequisicao", t.getMessage());
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        return v;
    }

    // clicar na lista
    @Override
    public void onRegistroClick(int position) {
        Toast.makeText(getContext(), "clicou na linha: " + position, Toast.LENGTH_SHORT).show();
        selecionado = listaRegistrosMes.get(position);

        bundleDadosRegistro = new Bundle();
        bundleDadosRegistro.putSerializable("registro", selecionado);
        Navigation.findNavController(getView()).navigate(R.id.mensal_to_editar_registro, bundleDadosRegistro);

    }

    private void inicializaComponentes(View v) {
        this.recyclerView = v.findViewById(R.id.recycler_mes);
        this.spMeses = v.findViewById(R.id.sp_meses);
        this.usuario = LoginActivity.usuarioLogado;
        this.registroService = RetrofitBuilder.buildRetrofit().create(RegistroService.class);

        this.listaRegistrosMes = new ArrayList<>();

        this.adapterMes = new AdapterMes(this.listaRegistrosMes, this);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        this.recyclerView.setAdapter(this.adapterMes);

        this.tvBom = v.findViewById(R.id.tv_bom_dias);
        this.tvMuitoBom = v.findViewById(R.id.tv_muito_bom_dias);
        this.tvHipo = v.findViewById(R.id.tv_hipo_dias);
        this.tvHiper = v.findViewById(R.id.tv_hiper_dias);

    }
}