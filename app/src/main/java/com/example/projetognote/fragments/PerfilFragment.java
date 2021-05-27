package com.example.projetognote.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.projetognote.R;
import com.example.projetognote.RetrofitBuilder;
import com.example.projetognote.UsuarioService;
import com.example.projetognote.activity.LoginActivity;
import com.example.projetognote.activity.MainActivity;
import com.example.projetognote.model.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilFragment extends Fragment {

    private Button btEditar;
    private Button btExcluir;
    private EditText et_nome_editar, et_email_editar, et_senha_atual_editar, et_nova_senha_editar;
    private Usuario usuario;
    private UsuarioService usuarioService;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_perfil, container, false);

        this.btEditar = v.findViewById(R.id.bt_editar);
        this.btExcluir = v.findViewById(R.id.bt_excluir);
        this.et_nome_editar = v.findViewById(R.id.et_nome_editar);
        this.et_email_editar = v.findViewById(R.id.et_email_editar);
        this.et_senha_atual_editar = v.findViewById(R.id.et_senha_atual_editar);
        this.et_nova_senha_editar = v.findViewById(R.id.et_nova_senha_editar);
        this.usuario = LoginActivity.usuarioLogado;
        this.usuarioService = RetrofitBuilder.buildRetrofit().create(UsuarioService.class);


        et_nome_editar.setText(usuario.getNome());
        et_email_editar.setText(usuario.getEmail());
//        et_senha_atual_editar.setText(usuario.getSenha());

        this.btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_senha_atual_editar.getText().toString().equals(usuario.getSenha())) {
                    usuario.setNome(et_nome_editar.getText().toString());
                    usuario.setEmail(et_email_editar.getText().toString());

                    if (et_nova_senha_editar == null) {
                        usuario.setSenha(et_senha_atual_editar.getText().toString());
                    } else {

                        usuario.setSenha(et_nova_senha_editar.getText().toString());
                    }

                    usuarioService.atualizar(usuario.getIdUsuario(), usuario).enqueue(new Callback<Usuario>() {
                        @Override
                        public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(getActivity(), "Usuario atualizado!", Toast.LENGTH_SHORT).show();
                                et_senha_atual_editar.setText("");
                                et_nova_senha_editar.setText("");
                            }
                        }

                        @Override
                        public void onFailure(Call<Usuario> call, Throwable t) {
                            Toast.makeText(getActivity(), "Não foi possível efetuar login. O servidor está fora, por favor tente mais tarde.", Toast.LENGTH_SHORT).show();
                        }
                    });
//                }else if(et_nova_senha_editar != null || et_senha_atual_editar.getText().toString().equals(et_nova_senha_editar.getText().toString())){
//                    Toast.makeText(getActivity(), "Senha não corresponde com a cadastrada.", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getActivity(), "Senha não corresponde com a cadastrada.", Toast.LENGTH_SHORT).show();

                }
            }
        });

        this.btExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuarioService.deletar(usuario.getIdUsuario()).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getActivity(), "Usuario excluido, desculpa qualquer coisa!", Toast.LENGTH_SHORT).show();
                            // voltar pra main
                            Intent voltarMain = new Intent(getActivity(), MainActivity.class);
                            startActivity(voltarMain);
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getActivity(), "Não foi possível efetuar login. O servidor está fora, por favor tente mais tarde.", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });

        return v;
    }


}