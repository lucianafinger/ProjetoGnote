package com.example.projetognote.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.projetognote.R;
import com.google.android.material.textfield.TextInputLayout;

public class AdicionarRegistroActivity extends AppCompatActivity {

    private TextInputLayout txtHora, txtGlicose, txtInsulinaRefeicao, txtInsulinaCorrecao;
    private EditText etHora, etGlicose, etInsulinaRefeicao, etInsulinaCorrecao;
    private Button btRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_registro);

        btRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //salvar registros
            }
        });


    }

    private void inicializaComponentes(){
        txtHora = findViewById(R.id.txt_hora);
        txtGlicose = findViewById(R.id.txt_glicose);
        txtInsulinaRefeicao = findViewById(R.id.txt_insulina_refeicao);
        txtInsulinaCorrecao = findViewById(R.id.txt_insulina_correcao);

        etHora = findViewById(R.id.et_hora);
        etGlicose = findViewById(R.id.et_glicose);
        etInsulinaRefeicao = findViewById(R.id.et_insulina_refeicao);
        etInsulinaCorrecao = findViewById(R.id.et_insulina_correcao);

        btRegistrar = findViewById(R.id.bt_registrar);
    }
}