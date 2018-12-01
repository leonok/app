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
import com.eng.planoestudos.modelo.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.database.DatabaseReference;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private Button btnRegistrar;
    private Button btnEsqueceuSenha;

    private EditText campoEmail;
    private EditText campoSenha;

    //Configuração
    private Usuario usuario;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.id_btnLogin);
        btnRegistrar = findViewById(R.id.id_btnRegistrar);
        btnEsqueceuSenha = findViewById(R.id.id_btnEsqueceusenha);

        campoEmail = findViewById(R.id.id_etEmail);
        campoSenha = findViewById(R.id.id_etSenha);

        // Verificar se Usuario ja esta logado, se sim, direciona direto para o App
        verificaUserLogado();


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                usuario = new Usuario();
                usuario.setEmail( campoEmail.getText().toString() );
                usuario.setSenha( campoSenha.getText().toString() );

                if ( validarDados() ) {
                    validarLogin();
                }

            }
        });


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startCadastro();
            }
        });

        btnEsqueceuSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startRecuperarSenha();
            }
        });

    }


    private void verificaUserLogado(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        if( autenticacao.getCurrentUser() != null){
            startApp();
        }
    }

    private void validarLogin(){

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if( task.isSuccessful() ){
                    Toast.makeText(LoginActivity.this, "Login efetuado com sucesso!", Toast.LENGTH_LONG ).show();
                    // Remover TOAST e colocar uma animação para inicar tela, ALGO ASSIM

                    startApp();
                }else{
                    //Toast.makeText(LoginActivity.this, "Erro ao logar!", Toast.LENGTH_LONG ).show();

                    try{
                        throw task.getException();

                    }catch ( FirebaseAuthInvalidUserException e){
                        indicaCampoComErro(campoEmail, "E-mail não cadastrado.");
                    } catch ( FirebaseAuthInvalidCredentialsException e) {
                        indicaCampoComErro(campoSenha, "Senha inválida.");
                    }  catch (Exception e) {
                        indicaCampoComErro(campoEmail, "ERROR: 404");
                        indicaCampoComErro(campoSenha, "ERROR: 404");
                        e.printStackTrace();
                    }

                }
            }
        });

    }

    private void startApp(){
        // Se login for realizado, direciona o usuário para PrimeiraTela ( tela principal )
        Intent intent = new Intent(LoginActivity.this, FirstActivity.class);
        startActivity(intent);
        finish();
    }

    private void startRecuperarSenha(){
        // Se usuário esqueceu a senha, é direcionado para tela de recuperar senha
        Intent intent = new Intent(LoginActivity.this, RecuperarsenhaActivity.class);
        startActivity(intent);
    }

    private void startCadastro(){
        // Se usuário não for cadastrado, ele é direcionado para tela de cadastro
        Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(intent);
    }

    private boolean validarDados(){
        boolean status = true;

        Validacao valida = new Validacao();

        if( !valida.ValidaEmail( usuario.getEmail() ) ){
            indicaCampoComErro(campoEmail, "E-mail inválido");
            return false;
        }

        if (usuario.getSenha().length() < 6) {
            indicaCampoComErro(campoSenha, "Senha inválida");
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
