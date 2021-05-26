package com.example.projetognote.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetognote.R;
import com.example.projetognote.RegistroService;
import com.example.projetognote.RetrofitBuilder;
import com.example.projetognote.model.Registro;
import com.example.projetognote.model.Usuario;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.material.textfield.TextInputLayout;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdicionarRegistroActivity extends AppCompatActivity {

    private TextInputLayout txtHora, txtGlicose, txtInsulinaRefeicao, txtInsulinaCorrecao;
    private EditText etHora, etGlicose, etInsulinaRefeicao, etInsulinaCorrecao;
    private Button btRegistrar;
    private ImageButton imgVoltar;

    private Registro registro;
    private RegistroService registroService;

    private Usuario usuario;

    public static List<Registro> listaReg = new ArrayList<Registro>();

    DateTimeFormatter hora = DateTimeFormatter.ofPattern ("HH:mm:ss");
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_registro);

        this.inicializaComponentes();
        this.usuario = LoginActivity.usuarioLogado;

        SimpleMaskFormatter smf = new SimpleMaskFormatter("NN:NN:NN");
        MaskTextWatcher mtw = new MaskTextWatcher(etHora, smf);
        etHora.addTextChangedListener(mtw);

        this.registro = new Registro();

        btRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                java.sql.Date dataReg = java.sql.Date.valueOf(data.format(new Date(System.currentTimeMillis())));
                registro.setDataRegistro(dataReg);
//                registro.setHoraRegistro(LocalTime.parse(etHora.getText().toString(),hora));

                // *************************
                // registro.setHoraRegistro(null);

                registro.setRegistroGlicose(Integer.parseInt(etGlicose.getText().toString()));
                registro.setInsulinaRefeicao(Double.parseDouble(etInsulinaRefeicao.getText().toString()));
                registro.setInsulinaCorrecao(Double.parseDouble(etInsulinaCorrecao.getText().toString()));

                registro.setUsuario(usuario);

                etiqueta();

                System.out.println(registro.getEtiqueta());
                System.out.println(usuario.getIdUsuario());
                System.out.println("insulina correcao: " +registro.getInsulinaCorrecao() + " insulina fixa: " + registro.getInsulinaRefeicao());
                System.out.print(registro.toString());
                System.out.println(usuario.toString());

                registroService.adicionarRegistro(registro).enqueue(new Callback<Registro>() {
                    @Override
                    public void onResponse(Call<Registro> call, Response<Registro> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Registro adicionado com sucesso", Toast.LENGTH_LONG).show();
                            onBackPressed();
                        }else{
                            Toast.makeText(getApplicationContext(), "Erro ao adicionar registro", Toast.LENGTH_LONG).show();
                            Log.i("DEBUG", response.errorBody().toString());
                        }
                    }
                    @Override
                    public void onFailure(Call<Registro> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Erro no registro: " + t.getMessage(), Toast.LENGTH_LONG).show();
                        t.printStackTrace();
                    }
                });
            }
        });

        imgVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // voltar tela anterior
                onBackPressed();
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

        this.imgVoltar = findViewById(R.id.img_voltar_adicionar_registro);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
