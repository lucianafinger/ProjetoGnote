package com.example.projetognote.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.projetognote.R;
import com.example.projetognote.RetrofitBuilder;
import com.example.projetognote.UsuarioService;
import com.example.projetognote.activity.LoginActivity;
import com.example.projetognote.model.Usuario;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentInsulina extends Fragment {

    private SeekBar sbHipo, sbHiper, sbIdealMin, sbIdealMax;
    private TextInputLayout txtGlicose, txtIntervalo, txtDoseInsulina;
    private EditText etGlicoseInicial, etIntervalo, etDoseInsulina;
    private Button btSalvar;

    private TextView tvProgressHipo, tvProgressHiper, tvProgressIdealMin, tvProgressIdealMax;

    private Usuario usuario;
    private UsuarioService usuarioService;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_insulina, container, false);

        usuario = LoginActivity.usuarioLogado;

        this.inicializaComponentes(v);

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

        this.btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario.setCorrecaoHgt(Integer.parseInt(etGlicoseInicial.getText().toString()));
                usuario.setIntervalo(Integer.parseInt(etIntervalo.getText().toString()));
                usuario.setInsulina(Double.parseDouble(etDoseInsulina.getText().toString()));

                usuarioService.atualizar(usuario.getIdUsuario(), usuario).enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getContext(), "Usuário atualizado com sucesso!", Toast.LENGTH_LONG).show();
                            limparCampos();
//                            startActivity(new Intent(InfoInsulinaActivity.this, LoginActivity.class));

                        } else {
                            Log.i("DEBUG", response.message());
                            Log.i("DEBUG", response.errorBody().toString());
                            Toast.makeText(getContext(), "Erro ao cadastrar Usuário.", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {
                        Log.i("DEBUG", t.getMessage());
                        Toast.makeText(getContext(), "Não foi possível cadastrar. O servidor está fora, por favor tente mais tarde.", Toast.LENGTH_LONG).show();
                    }
                });
            }

        });
        return v;
    }

    private void inicializaComponentes(View v) {
        sbHipo = v.findViewById(R.id.sb_hipo_atualizar);
        sbHiper = v.findViewById(R.id.sb_hiper_atualizar);
        sbIdealMin = v.findViewById(R.id.sb_ideal_min_atualizar);
        sbIdealMax = v.findViewById(R.id.sb_ideal_max_atualizar);
        btSalvar = v.findViewById(R.id.bt_salvar_insulina_atualizar);

        txtGlicose = v.findViewById(R.id.txt_glicose_inicial_atualizar);
        txtDoseInsulina = v.findViewById(R.id.txt_dose_intervalo_atualizar);
        txtIntervalo = v.findViewById(R.id.txt_intervalo_glicose_atualizar);

        etGlicoseInicial = v.findViewById(R.id.et_glicose_inicial_atualizar);
        etDoseInsulina = v.findViewById(R.id.et_dose_intervalo_atualizar);
        etIntervalo = v.findViewById(R.id.et_intervalo_glicose_atualizar);

        tvProgressHipo = v.findViewById(R.id.tv_progress_hipo_atualizar);
        tvProgressHiper = v.findViewById(R.id.tv_progress_hiper_atualizar);
        tvProgressIdealMin = v.findViewById(R.id.tv_progress_ideal_min_atualizar);
        tvProgressIdealMax = v.findViewById(R.id.tv_progress_ideal_max_atualizar);

        usuarioService = RetrofitBuilder.buildRetrofit().create(UsuarioService.class);
    }
    private void limparCampos(){
        etGlicoseInicial.setText("");
        etDoseInsulina.setText("");
        etIntervalo.setText("");
    }
}