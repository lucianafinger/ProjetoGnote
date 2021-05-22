package com.example.projetognote.activity;

import android.graphics.RegionIterator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetognote.R;
import com.example.projetognote.RegistroService;
import com.example.projetognote.RetrofitBuilder;
import com.example.projetognote.model.Registro;
import com.example.projetognote.model.Usuario;
import com.google.android.material.textfield.TextInputLayout;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdicionarRegistroActivity extends AppCompatActivity {

    private TextInputLayout txtHora, txtGlicose, txtInsulinaRefeicao, txtInsulinaCorrecao;
    private EditText etHora, etGlicose, etInsulinaRefeicao, etInsulinaCorrecao;
    private Button btRegistrar;

    private Registro registro;
    private RegistroService registroService;

    private Usuario usuario;


    DateTimeFormatter hora = DateTimeFormatter.ofPattern ("HH:mm:ss");
    SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_registro);

        this.inicializaComponentes();
        this.usuario = LoginActivity.usuariologado;
//        System.out.println("user" + usuario.getNome());
        this.registro = new Registro();

        btRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                java.sql.Date data_registro = java.sql.Date.valueOf(data.format( new Date(System.currentTimeMillis())));
                registro.setData_registro(data_registro);
//                System.out.println(data_registro);
                registro.setData_registro(data_registro);
                registro.setHoraRegistro(LocalTime.parse(etHora.getText().toString(),hora));

                System.out.println(registro.getHoraRegistro());

                registro.setHoraRegistro(null);
                registro.setRegistroGlicose(Integer.parseInt(etGlicose.getText().toString()));
                registro.setInsulinaFixa(Double.parseDouble(etInsulinaRefeicao.getText().toString()));
                registro.setInsulinaCorrecao(Double.parseDouble(etInsulinaCorrecao.getText().toString()));

                registro.setUsuario(usuario);
                etiqueta();

                System.out.println(registro.getEtiqueta());
                System.out.println(usuario.getId_usuario());
                System.out.println("insulina correcao: " +registro.getInsulinaCorrecao() + " insulina fixa: " + registro.getInsulinaFixa());

                System.out.print( registro.toString());
                registroService.adicionarRegistro(registro).enqueue(new Callback<Registro>() {
                    @Override
                    public void onResponse(Call<Registro> call, Response<Registro> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Registro adicionado com sucesso", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Erro ao adicionar registro", Toast.LENGTH_LONG).show();
                            Log.i("DEBUG", response.message().toString());
                            Log.i("DEBUG", response.errorBody().toString());

                        }
                    }

                    @Override
                    public void onFailure(Call<Registro> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Não foi possível efetuar login. O servidor está fora, por favor tente mais tarde." + t.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });


            }
        });


    }

    private void inicializaComponentes() {
        this.txtHora = findViewById(R.id.txt_hora);
        this.txtGlicose = findViewById(R.id.txt_glicose);
        this.txtInsulinaRefeicao = findViewById(R.id.txt_insulina_refeicao);
        this.txtInsulinaCorrecao = findViewById(R.id.txt_insulina_correcao);

        this.etHora = findViewById(R.id.et_hora);
        this.etGlicose = findViewById(R.id.et_glicose);
        this.etInsulinaRefeicao = findViewById(R.id.et_insulina_refeicao);
        this.etInsulinaCorrecao = findViewById(R.id.et_insulina_correcao);

        registro = new Registro();

        this.btRegistrar = findViewById(R.id.bt_registrar);
        this.registroService = RetrofitBuilder.buildRetrofit().create(RegistroService.class); // AJR - Gnote - 15/05/2021
    }

    private void etiqueta() {
        int glicose = Integer.parseInt(etGlicose.getText().toString());
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