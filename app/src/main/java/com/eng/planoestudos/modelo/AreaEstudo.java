package com.eng.planoestudos.modelo;

import java.util.ArrayList;


/**
 * Created by User on 27/11/2018.
 */

public class AreaEstudo {

    private String id;
    private String nome;
    private ArrayList<Materia> materias;
    //private ArrayList<Date> revisoes;
    private ArrayList<Integer> revisoes; // Quantidade de dias, para as revisoes

    public AreaEstudo() {

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

    public ArrayList<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(ArrayList<Materia> materias) {
        this.materias = materias;
    }

    public ArrayList<Integer> getRevisoes() {
        return revisoes;
    }

    public void setRevisoes(ArrayList<Integer> revisoes) {
        this.revisoes = revisoes;
    }
}
