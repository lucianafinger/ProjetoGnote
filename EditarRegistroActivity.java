package com.example.projetognote.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projetognote.R;
import com.example.projetognote.RegistroService;
import com.example.projetognote.RetrofitBuilder;
import com.example.projetognote.model.Registro;
import com.example.projetognote.model.Usuario;
import com.google.android.material.textfield.TextInputLayout;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditarRegistroActivity extends AppCompatActivity {

    private Registro registro;
    private TextInputLayout txtHoraEditar, txtGlicoseEditar, txtInsulinaRefeicaoEditar, txtInsulinaCorrecaoEditar;
    private EditText etHoraEditar, etGlicoseEditar, etInsulinaRefeicaoEditar, etInsulinaCorrecaoEditar;
    private Button btEditarRegistro, btExcluirRegistro;
    private RegistroService registroService;
    private Usuario usuario;
    DateTimeFormatter hora = DateTimeFormatter.ofPattern ("HH:mm:ss");
    SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_registro);

        registro = getIntent().getExtras().getParcelable("registro");

        usuario = LoginActivity.usuariologado;

        etHoraEditar.setText(String.valueOf(registro.getHoraRegistro()));
        etGlicoseEditar.setText(registro.getRegistroGlicose());
        etInsulinaRefeicaoEditar.setText(String.valueOf(registro.getInsulinaFixa()));
        etInsulinaCorrecaoEditar.setText(String.valueOf(registro.getInsulinaCorrecao()));


        this.btEditarRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                java.sql.Date data_registro = java.sql.Date.valueOf(data.format( new Date(System.currentTimeMillis())));
                registro.setData_registro(data_registro);
                registro.setHoraRegistro(LocalTime.parse(etHoraEditar.getText().toString(),hora));
                registro.setHoraRegistro(null);
                registro.setRegistroGlicose(Integer.parseInt(etGlicoseEditar.getText().toString()));
                registro.setInsulinaFixa(Double.parseDouble(etInsulinaRefeicaoEditar.getText().toString()));
                registro.setInsulinaCorrecao(Double.parseDouble(etInsulinaCorrecaoEditar.getText().toString()));
                registro.setUsuario(usuario);
                etiqueta();

                registroService.atualizar(registro.getIdRegistro(), registro).enqueue(new Callback<Registro>() {
                    @Override
                    public void onResponse(Call<Registro> call, Response<Registro> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Registro atualizado com sucesso", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Erro ao atualizar registro", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Registro> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Erro ao atualizar registro", Toast.LENGTH_LONG).show();

                    }
                });

            }
        });

        this.btExcluirRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registroService.deletar(registro.getIdRegistro()).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()){
                            etHoraEditar.setText(")");
                            etGlicoseEditar.setText("");
                            etInsulinaRefeicaoEditar.setText("");
                            etInsulinaCorrecaoEditar.setText("");

                            Toast.makeText(getApplicationContext(), "Registro excluido com sucesso", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Erro ao excluir registro", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Erro ao excluir registro", Toast.LENGTH_LONG).show();

                    }
                });
            }
        });




    }
    private void inicializaComponentes() {
        this.txtHoraEditar = findViewById(R.id.txt_hora_editar);
        this.txtGlicoseEditar = findViewById(R.id.txt_glicose_editar);
        this.txtInsulinaRefeicaoEditar = findViewById(R.id.txt_insulina_refeicao_editar);
        this.txtInsulinaCorrecaoEditar = findViewById(R.id.txt_insulina_correcao_editar);

        this.etHoraEditar = findViewById(R.id.et_hora_editar);
        this.etGlicoseEditar = findViewById(R.id.et_glicose_editar);
        this.etInsulinaRefeicaoEditar = findViewById(R.id.et_insulina_refeicao_editar);
        this.etInsulinaCorrecaoEditar= findViewById(R.id.et_insulina_correcao_editar);

        this.btEditarRegistro = findViewById(R.id.bt_editar_registro);
        this.btEditarRegistro = findViewById(R.id.bt_editar_registro);

        this.registroService = RetrofitBuilder.buildRetrofit().create(RegistroService.class); // AJR - Gnote - 15/05/2021
    }

    private void etiqueta() {
        int glicose = Integer.parseInt(etGlicoseEditar.getText().toString());
//                  Etiqueta de hiper
        if (glicose > usuario.getIdealMaxima() || glicose >= usuario.getHiperglicemia()) {
            registro.setEtiqueta("3");
//                  Etiqueta de hipo
        } else if (glicose < usuario.getIdealMinima() || glicose <= usuario.getHipoglicemia()) {
            registro.setEtiqueta("1");
//                  Etiqueta de bom
        } else if (glicose >= usuario.getIdealMinima() && glicose <= usuario.getIdealMaxima()) {
            registro.setEtiqueta("2");
        }

    }



}