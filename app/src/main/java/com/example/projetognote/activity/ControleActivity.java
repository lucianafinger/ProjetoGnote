package com.example.projetognote.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.projetognote.R;
import com.example.projetognote.RegistroService;
import com.example.projetognote.RetrofitBuilder;
import com.example.projetognote.UsuarioService;
import com.example.projetognote.fragments.PerfilFragment;
import com.example.projetognote.fragments.RegistrosFragment;
import com.example.projetognote.model.Usuario;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.w3c.dom.Text;

public class ControleActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private NavController navController;
    private Usuario usuario;

    private TextView nome, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controle);

        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        this.usuario = LoginActivity.usuarioLogado;
        // Nav Header
        View headView = navigationView.getHeaderView(0);
        nome = headView.findViewById(R.id.tv_nome_header);
        nome.setText(usuario.getNome());
        email = headView.findViewById(R.id.tv_email_header);
        email.setText(usuario.getEmail());

        // Tool Bar
        setSupportActionBar(toolbar);

        // Floating Button
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ControleActivity.this, AdicionarRegistroActivity.class));
            }
        });

        // Drawer Layout
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home_registros, R.id.nav_mensal, R.id.nav_perfil, R.id.nav_logout, R.id.nav_contato, R.id.nav_atualizar_dados)
                .setOpenableLayout(drawer)
                .build();
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("on resume");
    }

}