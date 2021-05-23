package com.example.projetognote.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
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
    private EditText etGlicoseInicial, etIntervalo, etDoseInsulina;
    private Button btRegistrar;

    private TextView tvProgressHipo, tvProgressHiper, tvProgressIdealMin, tvProgressIdealMax;

    private Usuario usuario;
    private UsuarioService usuarioService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_insulina);

        this.inicializaComponentes();

        usuario = getIntent().getExtras().getParcelable("usuario");

        // glicose
        this.sbHipo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvProgressHipo.setText(" " + progress);
                int progress_hipo = progress;
                usuario.setHipoglicemia(progress_hipo);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        this.sbHiper.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvProgressHiper.setText(" " + progress);
                int progress_hiper = progress;
                usuario.setHiperglicemia(progress_hiper);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        this.sbIdealMin.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvProgressIdealMin.setText(" " + progress);
                int progress_ideal_min = progress;
                usuario.setIdealMinima(progress_ideal_min);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        this.sbIdealMax.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvProgressIdealMax.setText(" " + progress);
                int progress_ideal_max = progress;
                usuario.setIdealMaxima(progress_ideal_max);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        this.btRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Bem vindo(a): " + usuario.getNome(), Toast.LENGTH_LONG).show();

                usuario.setCorrecaoHgt(Integer.parseInt(etGlicoseInicial.getText().toString()));
                usuario.setIntervalo(Integer.parseInt(etIntervalo.getText().toString()));
                usuario.setInsulina(Double.parseDouble(etDoseInsulina.getText().toString()));

                usuarioService.adicionar(usuario).enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Usuário cadastrado com sucesso!", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(InfoInsulinaActivity.this, LoginActivity.class));
                            finish();
                        } else {
                            Log.i("DEBUG", response.message());
                            Log.i("DEBUG", response.errorBody().toString());
                            Toast.makeText(getApplicationContext(), "Erro ao cadastrar Usuário.", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {
                        Log.i("DEBUG", t.getMessage());
                        Toast.makeText(getApplicationContext(), "Não foi possível cadastrar. O servidor está fora, por favor tente mais tarde.", Toast.LENGTH_LONG).show();
                    }
                });
            }

        });

    }

    private void inicializaComponentes(){
        sbHipo = findViewById(R.id.sb_hipo);
        sbHiper = findViewById(R.id.sb_hiper);
        sbIdealMin = findViewById(R.id.sb_ideal_min);
        sbIdealMax = findViewById(R.id.sb_ideal_max);
        btRegistrar = findViewById(R.id.bt_registrar_insulina);

        txtGlicose = findViewById(R.id.txt_glicose_inicial);
        txtDoseInsulina = findViewById(R.id.txt_dose_intervalo);
        txtIntervalo = findViewById(R.id.txt_intervalo_glicose);

        etGlicoseInicial = findViewById(R.id.et_glicose_inicial);
        etDoseInsulina = findViewById(R.id.et_dose_intervalo);
        etIntervalo = findViewById(R.id.et_intervalo_glicose);

        tvProgressHipo = findViewById(R.id.tv_progress_hipo);
        tvProgressHiper = findViewById(R.id.tv_progress_hiper);
        tvProgressIdealMin = findViewById(R.id.tv_progress_ideal_min);
        tvProgressIdealMax = findViewById(R.id.tv_progress_ideal_max);

        usuarioService = RetrofitBuilder.buildRetrofit().create(UsuarioService.class);
    }

}