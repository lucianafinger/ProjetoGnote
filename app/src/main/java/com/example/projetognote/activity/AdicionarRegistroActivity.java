package com.example.projetognote.activity;

import android.os.Bundle;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdicionarRegistroActivity extends AppCompatActivity {

    private TextInputLayout txtHora, txtGlicose, txtInsulinaRefeicao, txtInsulinaCorrecao;
    private EditText etHora, etGlicose, etInsulinaRefeicao, etInsulinaCorrecao;
    private Button btRegistrar;

    private Registro registro;
    private RegistroService registroService;

    Usuario usuario = LoginActivity.usuariologado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_registro);

        this.inicializaComponentes();

        this.registro = new Registro();

        btRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Date dataUtil = (Date) new java.util.Date();
//                Date dataSql = new java.sql.Date(dataUtil.getTime());
//
//                registro.setData_registro(dataSql);
//                registro.setHoraRegistro(Time.valueOf(etHora.getText().toString()));

                registro.setRegistroGlicose(Integer.parseInt(etGlicose.getText().toString()));
                registro.setInsulinaFixa(Double.parseDouble(etInsulinaRefeicao.getText().toString()));
                registro.setInsulinaCorrecao(Double.parseDouble(etInsulinaCorrecao.getText().toString()));
                registro.setUsuario(usuario);
//                etiqueta();

                System.out.println("insulina correcao: " +registro.getInsulinaCorrecao() + " insulina fixa: " + registro.getInsulinaFixa());

                registroService.adicionarRegistro(registro).enqueue(new Callback<Registro>() {
                    @Override
                    public void onResponse(Call<Registro> call, Response<Registro> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Registro adicionado com sucesso", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Erro ao adicionar registro", Toast.LENGTH_LONG).show();

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

//    private void etiqueta() {
//        int glicose = Integer.parseInt(etGlicose.getText().toString());
////                  Etiqueta de hiper
//        if (glicose > usuario.getIdealMaxima() || glicose >= usuario.getHiperglicemia()) {
//            registro.setEtiqueta("3");
////                  Etiqueta de hipo
//        } else if (glicose < usuario.getIdealMinima() || glicose <= usuario.getHipoglicemia()) {
//            registro.setEtiqueta("1");
////                  Etiqueta de bom
//        } else if (glicose >= usuario.getIdealMinima() && glicose <= usuario.getIdealMaxima()) {
//            registro.setEtiqueta("2");
//        }


//    }
}



