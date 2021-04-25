package com.example.projetognote;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class CadastroActivity extends AppCompatActivity implements Validator.ValidationListener {

    private TextInputEditText txtNome, txtEmail, txtSenha, txtConfirmaSenha;

    @NotEmpty(message = "Campo vazio")
    @Length(min = 3, max = 20, message = "O nome deve ter entre 3 a 20 caracteres!")
    private EditText etNome;
    @NotEmpty(message = "Campo vazio")
    @Email(message = "E-mail inválido")
    private EditText etEmail;
    @Password(min = 6, scheme = Password.Scheme.ALPHA_NUMERIC_MIXED_CASE_SYMBOLS)
    private EditText etSenha;
    @Password(min = 6, scheme = Password.Scheme.ALPHA_NUMERIC_MIXED_CASE_SYMBOLS)
    private EditText etConfirmaSenha;

    private CheckBox cbTermos;
    private Button btCadastro;

    private Validator validator;

    private Usuario usuario;
    public static ArrayList<Usuario> usuariosCadastrados = new ArrayList<>();

    // ficar funcional e apenas depois colocar material design (q ja está no buildgradle)!!!

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        this.inicializaComponentes();

        this.btCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
                // após validar o cadastro é encaminhado para a tela de login para confirmar cadastro e logar no app
                Intent intentTela2 = new Intent(CadastroActivity.this, LoginActivity.class);
                startActivity(intentTela2);
                // como já cadastrou, encerra essa aba
                finish();
            }
        });

    }

    private void inicializaComponentes(){
        this.etNome = findViewById(R.id.et_nome);
        this.etEmail = findViewById(R.id.et_email);
        this.etSenha = findViewById(R.id.txt_senha);
        this.etConfirmaSenha = findViewById(R.id.et_confirma_senha);

        this.txtNome = findViewById(R.id.et_nome);
        this.txtEmail = findViewById(R.id.et_email);
        this.txtSenha = findViewById(R.id.txt_senha);
        this.txtConfirmaSenha = findViewById(R.id.et_confirma_senha);

        this.cbTermos = findViewById(R.id.cb_termos);
        this.btCadastro = findViewById(R.id.bt_cadastrar);

        validator = new Validator(this);
        validator.setValidationListener(this);

        usuario = new Usuario();
        usuariosCadastrados = new ArrayList<>();
    }

    // se tudo der certo, executa:
    @Override
    public void onValidationSucceeded() {
        usuario.setNome(etNome.getText().toString());
        usuario.setEmail(etEmail.getText().toString());
        // comparar e validar senha e confirma senha pesquisar!!!
        usuario.setSenha(etSenha.getText().toString());
        usuariosCadastrados.add(usuario);
    }

    // se a validação falhar, executa:
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
                    case R.id.et_nome:
                        txtNome.setError(msgErro);
                        break;
                    case R.id.et_email:
                        txtEmail.setError(msgErro);
                        break;
                    // verificar como fazer para informar que as duas senhas não conferem!!!
                    case R.id.txt_senha:
                        txtSenha.setError(msgErro);
                        break;
                    case R.id.et_confirma_senha:
                        txtConfirmaSenha.setError(msgErro);
                        break;
                }
            }
        }
    }
}
