package com.eng.planoestudos.CodeTelas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.eng.planoestudos.R;

public class RedefinirsenhaActivity extends AppCompatActivity {

    private Button btnRedefinirSenha;

    private EditText campoSenhaAtual;
    private EditText campoNovaSenha;
    private EditText campoConfirmaNovaSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redefinirsenha);

        btnRedefinirSenha = findViewById(R.id.id_btnRedefSenhaTelaRedefSenha);

        campoSenhaAtual = findViewById(R.id.id_EtSenhaAtualTelaRedefSenha);
        campoNovaSenha = findViewById(R.id.id_EtNovaSenhaTelaRedefSenha);
        campoConfirmaNovaSenha = findViewById(R.id.id_EtConfirmaNovaSenhaTelaRedefSenha);


        btnRedefinirSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {





                Toast toast = Toast.makeText(getApplicationContext(), "Senha foi alterada !", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }


}


// COMO FAZER
/*
USUARIO TEM QUE ESTAR LOGADO  !!!!!

Definir a senha de um usuário
Defina a senha de um usuário com o método updatePassword. Por exemplo:

var user = firebase.auth().currentUser;
var newPassword = getASecureRandomPassword();

user.updatePassword(newPassword).then(function() {
  // Update successful.
}).catch(function(error) {
  // An error happened.
});


Importante: para que seja possível definir a senha de um usuário, é preciso que ele tenha feito login recentemente.

Consulte o artigo Reautenticar um usuário:
https://firebase.google.com/docs/auth/web/manage-users#re-authenticate_a_user

 */