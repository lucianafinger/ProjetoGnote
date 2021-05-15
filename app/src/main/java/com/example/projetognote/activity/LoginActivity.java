
package com.example.projetognote.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    private TextView tvEsqueceuSenha;
    private UsuarioService usuarioService;  // AJR - Gnote - 15/05/2021

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.inicializaComponentes();

        btLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // BEGIN of AJR - Gnote - 15/05/2021
                // verificar e passar p proxima tela


                String email = etEmail.getText().toString();
                String senha = etSenha.getText().toString();

                usuarioService.loginsenha(email, senha).enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Login Top", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(LoginActivity.this, ControleActivity.class));
                        } else {
                            Log.i("DEBUG", response.message().toString());
                            Log.i("DEBUG", response.errorBody().toString());
                            Toast.makeText(getApplicationContext(), "Erro no login" + response.message().toString(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {
                        Log.i("DEBUG", t.getMessage());
                        Toast.makeText(getApplicationContext(), "Não foi possível cadastrar. O servidor está fora, por favor tente mais tarde.", Toast.LENGTH_LONG).show();

                    }
                });


                // se nao, tente novamente

            }
        });

        tvEsqueceuSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // encaminha nova senha por e-mail já cadastrado
            }
        });
        // END of AJR - Gnote - 15/05/2021

    }

    private void inicializaComponentes() {
        etEmail = findViewById(R.id.et_email_login);
        etSenha = findViewById(R.id.et_senha_login); // AJR - Gnote - 15/05/2021
        btLogar = findViewById(R.id.bt_logar);
        tvEsqueceuSenha = findViewById(R.id.tv_esqueceu_senha);
        this.usuarioService = RetrofitBuilder.buildRetrofit().create(UsuarioService.class); // AJR - Gnote - 15/05/2021
    }

}
