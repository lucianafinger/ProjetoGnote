package com.example.projetognote.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import com.example.projetognote.R;
import com.google.android.material.textfield.TextInputLayout;

public class InfoInsulinaActivity extends AppCompatActivity {

    private SeekBar sbHipo, sbHiper, sbIdealMin, sbIdealMax;
    private TextInputLayout txtGlicose, txtIntervalo, txtDoseInsulina;
    private EditText etGlicose, etIntervalo, etDoseInsulina;
    private Button btRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_insulina);

        btRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InfoInsulinaActivity.this, LoginActivity.class));
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