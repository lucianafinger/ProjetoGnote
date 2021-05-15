package com.example.projetognote.fragments;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.projetognote.R;
import com.example.projetognote.activity.ControleActivity;

public class PerfilFragment extends Fragment {

    private Button btEditar;
    private Button btExcluir;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_perfil, container, false);

        this.btEditar = v.findViewById(R.id.bt_editar);
        this.btExcluir = v.findViewById(R.id.bt_excluir);

        this.btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Bundle info = new Bundle();
//                info.putString("teste", "valor do teste");
//                Navigation.findNavController(v).navigate(R.id.perfil_to_editar_perfil, info);

            }
        });

        this.btExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        return v;
    }
}