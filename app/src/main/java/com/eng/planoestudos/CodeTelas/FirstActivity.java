package com.eng.planoestudos.CodeTelas;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.eng.planoestudos.R;
import com.eng.planoestudos.config.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseAuth;

public class FirstActivity extends BaseActivity {

    private Button btnLogout;

    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        btnLogout = findViewById(R.id.id_btnLogout);


        // novo método para abrir Activity
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);

        // inflar seu layout de activity aqui!
        @SuppressLint("InflateParams")
        View contentView = inflater.inflate(R.layout.activity_first, null, false);
        drawer.addView(contentView, 0);
        navigationView.setCheckedItem(R.id.nav_first);


      /*  btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
               // autenticacao.signOut();
               // startLogin();
            }
       });
    */
    }

    private void startLogin(){
        // Se login for realizado, direciona o usuário para PrimeiraTela ( tela principal )
        Intent intent = new Intent(FirstActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void btnSair(View view){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signOut();
        startLogin();

    }

}
