package com.example.projetognote.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.projetognote.R;
import com.example.projetognote.RegistroService;
import com.example.projetognote.RetrofitBuilder;
import com.example.projetognote.activity.LoginActivity;
import com.example.projetognote.model.Registro;
import com.example.projetognote.model.Usuario;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputLayout;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditarRegistroFragment extends Fragment {

    private Registro registro;
    private TextInputLayout txtHoraEditar, txtGlicoseEditar, txtInsulinaRefeicaoEditar, txtInsulinaCorrecaoEditar;
    private EditText etHoraEditar, etGlicoseEditar, etInsulinaRefeicaoEditar, etInsulinaCorrecaoEditar;
    private Button btEditarRegistro, btExcluirRegistro;
    private RegistroService registroService;
    private Usuario usuario;
    private DateTimeFormatter hora = DateTimeFormatter.ofPattern ("HH:mm:ss");
    private SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd");

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_editar_registro, container, false);

        registro = getArguments().getParcelable("registro");

        System.out.println(registro.getIdRegistro());

        inicializaComponentes(v);

        this.etHoraEditar.setText(registro.getHoraRegistro().format(hora));
        this.etGlicoseEditar.setText(String.valueOf(registro.getRegistroGlicose()));
        this.etInsulinaRefeicaoEditar.setText(String.valueOf(registro.getInsulinaRefeicao()));
        this.etInsulinaCorrecaoEditar.setText(String.valueOf(registro.getInsulinaCorrecao()));

        btEditarRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                java.sql.Date dataReg = java.sql.Date.valueOf(data.format(new Date(System.currentTimeMillis())));
                registro.setDataRegistro(registro.getDataRegistro());
                registro.setHoraRegistro(LocalTime.parse(etHoraEditar.getText().toString(),hora));
                registro.setRegistroGlicose(Integer.parseInt(etGlicoseEditar.getText().toString()));
                registro.setInsulinaRefeicao(Double.parseDouble(etInsulinaRefeicaoEditar.getText().toString()));
                registro.setInsulinaCorrecao(Double.parseDouble(etInsulinaCorrecaoEditar.getText().toString()));
                registro.setUsuario(usuario);
                etiqueta();

                registroService.atualizar(registro.getIdRegistro(), registro).enqueue(new Callback<Registro>() {
                    @Override
                    public void onResponse(Call<Registro> call, Response<Registro> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(getContext(), "Registro atualizado com sucesso", Toast.LENGTH_LONG).show();

                        }else{
                            Toast.makeText(getContext(), "Erro ao atualizar registro", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Registro> call, Throwable t) {
                        Log.i("Teste", "socorro" + t.getMessage());
                        Toast.makeText(getContext(), "Erro onFailure", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

        btExcluirRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registroService.deletar(registro.getIdRegistro()).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()){
                            etHoraEditar.setText("");
                            etGlicoseEditar.setText("");
                            etInsulinaRefeicaoEditar.setText("");
                            etInsulinaCorrecaoEditar.setText("");

                            Toast.makeText(getContext(), "Registro excluido com sucesso", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getContext(), "Erro ao excluir registro", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.i("Teste", "socorro2" + t.getMessage());
                        Toast.makeText(getContext(), "Erro ao excluir registro - onFailure", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

        return v;

    }
    private void inicializaComponentes(View v) {
        this.txtHoraEditar = v.findViewById(R.id.txt_hora_reg_editar);
        this.txtGlicoseEditar = v.findViewById(R.id.txt_glicose_reg_editar);
        this.txtInsulinaRefeicaoEditar = v.findViewById(R.id.txt_insulina_refeicao_editar);
        this.txtInsulinaCorrecaoEditar = v.findViewById(R.id.txt_insulina_correcao_editar);

        this.etHoraEditar = v.findViewById(R.id.et_hora_reg_editar);
        this.etGlicoseEditar = v.findViewById(R.id.et_glicose_reg_editar);
        this.etInsulinaRefeicaoEditar = v.findViewById(R.id.et_insulina_refeicao_editar);
        this.etInsulinaCorrecaoEditar= v.findViewById(R.id.et_insulina_correcao_editar);

        this.btEditarRegistro = v.findViewById(R.id.bt_editar_registro);
        this.btExcluirRegistro = v.findViewById(R.id.bt_excluir_registro);

        this.usuario = LoginActivity.usuarioLogado;

        this.registroService = RetrofitBuilder.buildRetrofit().create(RegistroService.class);
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
