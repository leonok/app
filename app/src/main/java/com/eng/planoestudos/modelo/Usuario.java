package com.eng.planoestudos.modelo;


import com.eng.planoestudos.config.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

/**
 * Created by User on 27/11/2018.
 */



public class Usuario {

    private String id;
    private String nome;
    private String email;
    private String senha;

    // construtor
    public Usuario() {

    }

    public void salvar(){

        // Cria um Nó usuario, se ja existe somente insere.
        // Cada usuario é inserido em um nó, com a chave ID user Auth.
        // O valor salvo no nó ID, é todos os dados do USUARIO
        DatabaseReference referenciaFirebase = ConfiguracaoFirebase.getFirebase();
        referenciaFirebase.child("usuarios").child( getId() ).setValue( this );

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Notação, para não salvar a SENHA no banco, pq a senha ja esta salva no AUTH
    @Exclude
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
