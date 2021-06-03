package com.example.projetognote.activity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetognote.R;
import com.example.projetognote.RetrofitBuilder;
import com.example.projetognote.UsuarioService;
import com.example.projetognote.model.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etSenha;
    private Button btLogar;
    private Usuario user;
    private UsuarioService usuarioService;  // AJR - Gnote - 15/05/2021
    public static Usuario usuarioLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.inicializaComponentes();

        btLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // BEGIN of AJR - Gnote - 15/05/2021

                String email = etEmail.getText().toString();
                String senha = etSenha.getText().toString();

                usuarioService.login(email, senha).enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                        if (response.isSuccessful()){
                            if (response.body() == null){
                                Toast.makeText(getApplicationContext(), "Erro ao efetuar login. Verifique a senha e tente novamente", Toast.LENGTH_LONG).show();
                            }else {
                                usuarioLogado = response.body();
                                Toast.makeText(getApplicationContext(), "Bem vindo", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(LoginActivity.this, ControleActivity.class));
                                finish();
                            }
                        }else {
                            Toast.makeText(getApplicationContext(), "Erro no login. Verifique email e senha!" + response.message(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Não foi possível efetuar login. O servidor está fora, por favor tente mais tarde." + t.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });
            }
        });

    }

    private void inicializaComponentes() {
        etEmail = findViewById(R.id.et_email_login);
        etSenha = findViewById(R.id.et_senha_login); // AJR - Gnote - 15/05/2021
        btLogar = findViewById(R.id.bt_logar);
        this.usuarioService = RetrofitBuilder.buildRetrofit().create(UsuarioService.class); // AJR - Gnote - 15/05/2021
    }

}