package com.eng.planoestudos.CodeTelas;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.eng.planoestudos.R;
import com.eng.planoestudos.codeAux.Validacao;
import com.eng.planoestudos.config.ConfiguracaoFirebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class RecuperarsenhaActivity extends AppCompatActivity {

    private Button btnRecuperarSenha;
    private EditText campoEmail;

    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperarsenha);

        btnRecuperarSenha = findViewById(R.id.id_btnEnviarNovaSenha);
        campoEmail = findViewById(R.id.id_etEmailRecupera);

        btnRecuperarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Usuário informa seu email,
                // sistema envia um email com LINK para o usuario redefinir a senha.


                Validacao valida = new Validacao();
                if( !valida.ValidaEmail( campoEmail.getText().toString() ) ){
                    indicaCampoComErro(campoEmail, "E-mail invalido");
                }else{

                  // NOVO
                    autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
                    autenticacao.sendPasswordResetEmail( campoEmail.getText().toString() )
                            .addOnCompleteListener(RecuperarsenhaActivity.this, new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if( task.isSuccessful() ){
                                        // Após o email ser enviado, retorna para tela de login
                                        Toast.makeText(RecuperarsenhaActivity.this, "E-mail enviado com sucesso.", Toast.LENGTH_LONG ).show();

                                        Intent intent = new Intent(RecuperarsenhaActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                    }else{
                                        Toast.makeText(RecuperarsenhaActivity.this, "ERROR.", Toast.LENGTH_LONG ).show();
                                        //TRATAR AS EXCEÇÕES DO FIREBASE  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                                    }

                                }
                            });
                }
            }
        });

    }

    void indicaCampoComErro(EditText campo, String msg){
        campo.setText("");
        campo.setHint(msg);
        campo.getBackground().mutate().setColorFilter(getResources().getColor(R.color.vermelho), PorterDuff.Mode.SRC_ATOP);
    }

}
