package com.example.projetognote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText etEmail = findViewById(R.id.et_email);
        final EditText etSenha = findViewById(R.id.txt_senha);
        Button btLogar = findViewById(R.id.bt_logar);

        btLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = etEmail.getText().toString();
                String senha = etSenha.getText().toString();
                if(login.equals("pedro")&&senha.equals("123")){
                    alert("Login válido");
                }else{
                    alert("Login inválido");
                }
            }
        });

    }
    private void alert(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }
}