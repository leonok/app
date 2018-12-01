package com.eng.planoestudos.CodeTelas;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.eng.planoestudos.R;
import com.eng.planoestudos.codeAux.Validacao;
import com.eng.planoestudos.config.ConfiguracaoFirebase;
import com.eng.planoestudos.modelo.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.Random;

public class SignupActivity extends AppCompatActivity {

    // Elementos
    private Button btnRegistrar;
    private EditText campoNome;
    private EditText campoEmail;
    private EditText campoSenha;
    private EditText campoConfirmaSenha;

    // Configuração
    private Usuario usuario;
    private FirebaseAuth autenticacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Find
        btnRegistrar = findViewById(R.id.id_btnRegistrarSignup);
        campoNome = findViewById(R.id.id_etNomeSignup);
        campoEmail = findViewById(R.id.id_etEmailSignup);
        campoSenha = findViewById(R.id.id_etSenhaSignup);
        campoConfirmaSenha = findViewById(R.id.id_etSenhaSignup2);

        // Eventos
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                usuario = new Usuario();

                usuario.setNome( campoNome.getText().toString() );
                usuario.setEmail( campoEmail.getText().toString() );
                usuario.setSenha( campoSenha.getText().toString() );


                if ( validarDados() ){
                    // Desativa botao ( para usuario não ficar clicando e bugar servidor )
                    btnRegistrar.setEnabled(false);

                    //Colocar aquele circuilo girando, para demostrar que por tras esta efetuando um processo
                    // pq o cadastro leva alguns segundos

                    // Cadastra Novo usuario
                    cadastrarUsuario();

                    // ERRO: SALVAR DADOS EM MODO DE PROTEÇÃO !!!!!
                }


         /*
            // Gerar Token - Para confirmar o Registro
            // Ver se tem como fazer isso no FIREBASE
            Random randomico = new Random();
            int numToken = randomico.nextInt( (9999 - 1000 ) +1000 ); // Token ex: 1111 (4digitos)
            String token = String.valueOf( numToken );
            Log.i("Token: ", token);
         */

            }
        });

    }

    private void cadastrarUsuario(){

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if( task.isSuccessful() ){

                    // Retorna o usuariio, e pega o ID, ( id que o firebase Auth criou automaticamente )
                    FirebaseUser usuarioFirebase = task.getResult().getUser();
                    usuario.setId( usuarioFirebase.getUid() );

                    usuario.salvar();

                    Toast.makeText(SignupActivity.this, "Cadastro efetuado com sucesso.", Toast.LENGTH_LONG ).show();
                    startApp();
                }else {

                    try{
                        throw task.getException();

                    }catch ( FirebaseAuthWeakPasswordException e){
                        indicaCampoComErro(campoSenha, "Senha inválida.");
                    } catch ( FirebaseAuthInvalidCredentialsException e) {
                        indicaCampoComErro(campoEmail, "E-mail inválido.");
                    } catch ( FirebaseAuthUserCollisionException e) {
                        indicaCampoComErro(campoEmail, "E-mail já cadastrado.");
                    } catch (Exception e) {
                        indicaCampoComErro(campoNome, "ERROR 404");
                        e.printStackTrace();
                    }

                }
            }
        });

    }


    private void startApp(){
        // Firebase faz login automaticamente quando um novo usuario é cadastrado
        // Redirecionar usuario para tela principal
        Intent intent = new Intent(SignupActivity.this, FirstActivity.class);
        startActivity(intent);
    }

    private void testeIdUser(){
        Toast.makeText(SignupActivity.this, "Nome: "+usuario.getNome()+"  ID:"+usuario.getId(), Toast.LENGTH_LONG ).show();
    }

    private boolean validarDados(){
        boolean status = true;

        Validacao valida = new Validacao();

        if ( !valida.ValidaNome( usuario.getNome() ) ){

            indicaCampoComErro(campoNome, "Nome inválido");
            return false;
        }
        if( !valida.ValidaEmail( usuario.getEmail() ) ){

            indicaCampoComErro(campoEmail, "E-mail inválido");
            return false;
        }
        if( usuario.getSenha().equals( campoConfirmaSenha.getText().toString() ) ) {
            if (usuario.getSenha().length() < 6) {

                indicaCampoComErro(campoSenha, "Senha deve conter no mínimo 6 digitos");
                return false;
            }
        }else{

            indicaCampoComErro(campoSenha, "Senhas devem ser iguais");
            return false;
        }

        return status;
    }

    void indicaCampoComErro(EditText campo, String msg){
        campo.setText("");
        campo.setHint(msg);
        campo.getBackground().mutate().setColorFilter(getResources().getColor(R.color.vermelho), PorterDuff.Mode.SRC_ATOP);
    }


}
