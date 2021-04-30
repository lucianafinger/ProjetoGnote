package com.example.projetognote;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class CadastroActivity extends AppCompatActivity implements Validator.ValidationListener {

    private TextInputLayout txtNome, txtEmail, txtSenha, txtConfirmaSenha;

    @NotEmpty(message = "Campo vazio")
    @Length(min = 3, max = 20, message = "O nome deve ter entre 3 a 20 caracteres!")
    private EditText etNome;
    @NotEmpty(message = "Campo vazio")
    @Email(message = "E-mail inválido")
    private EditText etEmail;
    // arrumar senha cadastro
//    @Password(min = 6, scheme = Password.Scheme.ALPHA_NUMERIC_MIXED_CASE_SYMBOLS)
//    private EditText etSenha;
//    @Password(min = 6, scheme = Password.Scheme.ALPHA_NUMERIC_MIXED_CASE_SYMBOLS)
//    private EditText etConfirmaSenha;

    private Switch swtTermos;
    private Button btCadastrar;

    private Validator validator;

    private Usuario usuario;
    public static ArrayList<Usuario> usuariosCadastrados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        this.inicializaComponentes();

        this.btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
//                if(validator.isValidating()){
//                    Intent telaCadastro2 = new Intent(CadastroActivity.this, CadastroDoisActivity.class);
//                    startActivity(telaCadastro2);
//                }
            }

        });

    }

    private void inicializaComponentes(){
        this.etNome = findViewById(R.id.et_nome);
        this.etEmail = findViewById(R.id.et_email);
//        this.etSenha = findViewById(R.id.et_senha);
//        this.etConfirmaSenha = findViewById(R.id.et_confirma_senha);

        this.txtNome = findViewById(R.id.txt_nome);
        this.txtEmail = findViewById(R.id.txt_email);
        this.txtSenha = findViewById(R.id.txt_senha);
        this.txtConfirmaSenha = findViewById(R.id.txt_confirma_senha);

        this.swtTermos = findViewById(R.id.swt_termos);
        this.btCadastrar = findViewById(R.id.bt_cadastrar);

        validator = new Validator(this);
        validator.setValidationListener(this);

        usuario = new Usuario();
        usuariosCadastrados = new ArrayList<>();
    }

    @Override
    public void onValidationSucceeded() {
        usuario.setNome(etNome.getText().toString());
        usuario.setEmail(etEmail.getText().toString());
        // comparar e validar senha e confirma senha pesquisar!!!
//        usuario.setSenha(etSenha.getText().toString());
        usuariosCadastrados.add(usuario);
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
                    case R.id.et_nome:
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
}
