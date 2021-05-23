package com.example.projetognote.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.projetognote.R;

public class MainActivity extends AppCompatActivity {

    private Button btCadastro;
    private Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.inicializaComponentes();

        this.btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaLogin = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(telaLogin);
            }
        });

        this.btCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaCadastro = new Intent(MainActivity.this, CadastroActivity.class);
                startActivity(telaCadastro);
            }
        });

    }

    private void inicializaComponentes(){
        btCadastro = findViewById(R.id.bt_cadastro);
        btLogin = findViewById(R.id.bt_login);
    }
}