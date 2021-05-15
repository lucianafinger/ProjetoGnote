package com.example.projetognote.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.projetognote.R;
import com.example.projetognote.RetrofitBuilder;
import com.example.projetognote.UsuarioService;
import com.example.projetognote.model.Usuario;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoInsulinaActivity extends AppCompatActivity {

    private SeekBar sbHipo, sbHiper, sbIdealMin, sbIdealMax;
    private TextInputLayout txtGlicose, txtIntervalo, txtDoseInsulina;
    private EditText etGlicose, etIntervalo, etDoseInsulina;
    private Button btRegistrar;

    private Usuario usuario;
    private UsuarioService usuarioService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_insulina);

        this.inicializaComponentes();

        btRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(InfoInsulinaActivity.this, ControleActivity.class));
            }
        });

    }

    private void inicializaComponentes(){
        sbHipo = findViewById(R.id.sb_hipo);
        sbHiper = findViewById(R.id.sb_hiper);
        sbIdealMin = findViewById(R.id.sb_ideal_min);
        sbIdealMax = findViewById(R.id.sb_ideal_max);
        btRegistrar = findViewById(R.id.bt_registrar_insulina);

    }


}