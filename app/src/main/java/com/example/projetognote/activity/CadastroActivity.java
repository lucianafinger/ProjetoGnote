package com.example.projetognote.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projetognote.R;
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

public class CadastroActivity extends AppCompatActivity implements Validator.ValidationListener {

    private TextInputLayout txtNome, txtEmail, txtSenha, txtConfirmaSenha;

    @NotEmpty(message = "Preencher nome")
    @Length(min = 3, max = 20, message = "O nome deve ter entre 3 a 20 caracteres!")
    private EditText etNome;
    @NotEmpty(message = "Preencher e-mail")
    @Email(message = "E-mail inválido")
    private EditText etEmail;
//    @Password(min = 6, scheme = Password.Scheme.ALPHA_MIXED_CASE)
    private EditText etSenha;
//    @Password(min = 6, scheme = Password.Scheme.ALPHA_MIXED_CASE)
    private EditText etConfirmaSenha;

    private Button btCadastrar;

    private Validator validator;
    private Usuario usuario;

    private Bundle bundleDadosUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        this.inicializaComponentes();

        this.btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
            }

        });
    }

        @Override
    public void onValidationSucceeded() {
        if(etConfirmaSenha.getText().toString().equals(etSenha.getText().toString())){
            usuario.setEmail(etEmail.getText().toString());
            usuario.setSenha(etSenha.getText().toString());
            usuario.setNome(etNome.getText().toString());

            bundleDadosUsuario = new Bundle();
            bundleDadosUsuario.putSerializable("usuario", usuario);
            Intent cadastroInsulina = new Intent(CadastroActivity.this, InfoInsulinaActivity.class);
            cadastroInsulina.putExtras(bundleDadosUsuario);
            startActivity(cadastroInsulina);

            Toast.makeText(this, "Cadastrando", Toast.LENGTH_LONG).show();
        } else{
            Toast.makeText(this, "As senhas não conferem! Tente novamente", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for(ValidationError e:errors){
            View v = e.getView();
            String msgErro = e.getCollatedErrorMessage(this);
            //decobrir que tipo de componente deu erro
            if(v instanceof TextInputEditText){
                switch (v.getId()){
                    case R.id.et_nome_editar:
                        txtNome.setError(msgErro);
                        break;
                    case R.id.et_email_login:
                        txtEmail.setError(msgErro);
                        break;
                    case R.id.et_senha:
                        txtSenha.setError(msgErro);
                        break;
                    case R.id.et_confirma_senha:
                        txtSenha.setError(msgErro);
                        break;
                }
            }
        }
    }

    private void inicializaComponentes(){
        this.etNome = findViewById(R.id.et_nome);
        this.etEmail = findViewById(R.id.et_email);
        this.etSenha = findViewById(R.id.et_senha);
        this.etConfirmaSenha = findViewById(R.id.et_confirma_senha);

        this.txtNome = findViewById(R.id.txt_nome);
        this.txtEmail = findViewById(R.id.txt_email);
        this.txtSenha = findViewById(R.id.txt_senha);
        this.txtConfirmaSenha = findViewById(R.id.txt_confirma_senha);

        this.btCadastrar = findViewById(R.id.bt_cadastrar);

        validator = new Validator(this);
        validator.setValidationListener(this);

        usuario = new Usuario();
    }
}
