package com.example.projetognote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.projetognote.R;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etSenha;
    private Button btLogar;
    private TextView tvEsqueceuSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.inicializaComponentes();

        // validar Login
        btLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // VÁLIDO - valida, pega a informação do server e vai p/ tela login
//                String email = etEmail.getText().toString();
//                String senha = etSenha.getText().toString();

                // NÃO VÁLIDO - retorna msg de erro

            }
        });

        tvEsqueceuSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // encaminha nova senha por e-mail já cadastrado
            }
        });

//        btLogar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String login = etEmail.getText().toString();
//                String senha = etSenha.getText().toString();
//                if(login.equals("luciana")&&senha.equals("123")){
//                    alert("Login válido");
//                }else{
//                    alert("Login inválido");
//                }
//            }
//        });

    }

    private void inicializaComponentes(){
        etEmail = findViewById(R.id.et_email);
        etSenha = findViewById(R.id.et_senha);
        btLogar = findViewById(R.id.bt_logar);
        tvEsqueceuSenha = findViewById(R.id.tv_esqueceu_senha);
    }

}