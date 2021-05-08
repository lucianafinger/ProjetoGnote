package com.example.projetognote.activity;

import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projetognote.InfoInsulinaActivity;
import com.example.projetognote.R;
import com.example.projetognote.UsuarioService;
import com.example.projetognote.model.Usuario;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroActivity extends AppCompatActivity implements Validator.ValidationListener {

    private TextInputLayout txtNome, txtEmail, txtSenha, txtConfirmaSenha;

    @NotEmpty(message = "Campo vazio")
    @Length(min = 3, max = 20, message = "O nome deve ter entre 3 a 20 caracteres!")
    private EditText etNome;
    @NotEmpty(message = "Campo vazio")
    @Email(message = "E-mail inválido")
    private EditText etEmail;
    // arrumar senha cadastro
    @Password(min = 6, scheme = Password.Scheme.ALPHA_NUMERIC_MIXED_CASE_SYMBOLS)
    private EditText etSenha;

    private Button btCadastrar;

    private Validator validator;

    private Usuario usuario;
    private UsuarioService usuarioService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

//        this.usuario = getArguments().getParcelable("usuario");

        this.inicializaComponentes();

        this.btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();

            }

        });

    }

    public void cadastrarUsuario(){
        usuarioService.adicionar(usuario).enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Usuário cadastrado com sucesso!", Toast.LENGTH_LONG).show();
                }else{
                    Log.i("DEBUG", response.message());
                    Log.i("DEBUG", response.errorBody().toString());
                    Toast.makeText(getApplicationContext(), "Erro ao cadastrar produto.", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.i("DEBUG", t.getMessage());
                Toast.makeText(getApplicationContext(), "Não foi possível cadastrar. O servidor está fora, por favor tente mais tarde.", Toast.LENGTH_LONG).show();
            }
        });

    }
        @Override
    public void onValidationSucceeded() {
        this.cadastrarUsuario();
        startActivity(new Intent(CadastroActivity.this, InfoInsulinaActivity.class));
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for(ValidationError e:errors){
            // pegar componente q deu erro
            View v = e.getView();
            // pegar msg de erro
            String msgErro = e.getCollatedErrorMessage(this);
            //decobrir que tipo de componente deu erro
            if(v instanceof TextInputEditText){
                //descobrir qual componente deu erro
                switch (v.getId()){
                    case R.id.et_nome_editar:
                        txtNome.setError(msgErro);
                        break;
                    case R.id.et_email:
                        txtEmail.setError(msgErro);
                        break;
                    // verificar como fazer para informar que as duas senhas não conferem!!!
                    case R.id.et_senha:
                        txtSenha.setError(msgErro);
                        break;
                    case R.id.et_confirma_senha:
                        txtConfirmaSenha.setError(msgErro);
                        break;
                }
            }
        }
    }

    private void inicializaComponentes(){
        this.etNome = findViewById(R.id.et_nome_editar);
        this.etEmail = findViewById(R.id.et_email);
        this.etSenha = findViewById(R.id.et_senha);

        this.txtNome = findViewById(R.id.txt_nome);
        this.txtEmail = findViewById(R.id.txt_email);
        this.txtSenha = findViewById(R.id.txt_senha);
        this.txtConfirmaSenha = findViewById(R.id.txt_confirma_senha);

        this.btCadastrar = findViewById(R.id.bt_cadastrar);

        validator = new Validator(this);
        validator.setValidationListener(this);

        usuario = new Usuario();
//        usuariosCadastrados = new ArrayList<>();
    }
}