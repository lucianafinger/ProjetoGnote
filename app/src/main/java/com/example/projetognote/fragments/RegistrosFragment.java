package com.example.projetognote.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetognote.adapter.AdapterRegistros;
import com.example.projetognote.R;
import com.example.projetognote.model.Registro;

import java.util.ArrayList;
import java.util.List;

public class RegistrosFragment extends Fragment implements AdapterRegistros.OnRegistroListener {

    private RecyclerView recyclerView;
    private List<Registro> listaRegistros;
    private AdapterRegistros adapterRegistros;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_registros, container, false);

        recyclerView = v.findViewById(R.id.rv_registros);

        // listagem de registros

        // configurar adapter
        this.listaRegistros = new ArrayList<>();

        this.adapterRegistros = new AdapterRegistros(this.listaRegistros, this);
        this.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false));
        this.recyclerView.setAdapter(this.adapterRegistros);

        // configurar recyclerview
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterRegistros);

        // clica no item da lista e passa pra tela editar/excluir

        return v;
    }

    @Override
    public void onRegistroClick(int position) {
    }
}