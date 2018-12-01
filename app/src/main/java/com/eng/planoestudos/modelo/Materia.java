package com.eng.planoestudos.modelo;

/**
 * Created by User on 27/11/2018.
 */

public class Materia {

    private String id; // Id do firebase é uma string ( TESTAR )
    private String nome;
    private int qtdHorasEstudadas;
    private float desempenho;

    public Materia() {

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

    public int getQtdHorasEstudadas() {
        return qtdHorasEstudadas;
    }

    public void setQtdHorasEstudadas(int qtdHorasEstudadas) {
        this.qtdHorasEstudadas = qtdHorasEstudadas;
    }

    public float getDesempenho() {
        return desempenho;
    }

    public void setDesempenho(float desempenho) {
        this.desempenho = desempenho;
    }
}

