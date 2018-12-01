package com.eng.planoestudos.modelo;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by User on 27/11/2018.
 */

public class Topico {

    private Integer id;
    private String idMateria;
    private String nome;
    private Date dataEstudo;
    private float tempoEstudo; // Ver como controlar o tempo de estudo
    private int qtdExercicios;
    private int qtdAcerto;
    private ArrayList<Date> proximasRevisoes;
    private ArrayList<Date> revisoesPendentes;


    public Topico() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(String idMateria) {
        this.idMateria = idMateria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataEstudo() {
        return dataEstudo;
    }

    public void setDataEstudo(Date dataEstudo) {
        this.dataEstudo = dataEstudo;
    }

    public float getTempoEstudo() {
        return tempoEstudo;
    }

    public void setTempoEstudo(float tempoEstudo) {
        this.tempoEstudo = tempoEstudo;
    }

    public int getQtdExercicios() {
        return qtdExercicios;
    }

    public void setQtdExercicios(int qtdExercicios) {
        this.qtdExercicios = qtdExercicios;
    }

    public int getQtdAcerto() {
        return qtdAcerto;
    }

    public void setQtdAcerto(int qtdAcerto) {
        this.qtdAcerto = qtdAcerto;
    }

    public ArrayList<Date> getProximasRevisoes() {
        return proximasRevisoes;
    }

    public void setProximasRevisoes(ArrayList<Date> proximasRevisoes) {
        this.proximasRevisoes = proximasRevisoes;
    }

    public ArrayList<Date> getRevisoesPendentes() {
        return revisoesPendentes;
    }

    public void setRevisoesPendentes(ArrayList<Date> revisoesPendentes) {
        this.revisoesPendentes = revisoesPendentes;
    }
}
