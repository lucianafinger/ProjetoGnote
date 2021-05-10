package com.example.projetognote.model;

import java.sql.Date;
import java.sql.Time;

public class Registro {
    private long idRegistro;
    private int registroGlicose;
    private Date dataRegistro;
    private Time horaRegistro;
    private String etiqueta;
    private double insulinaCorrecao, insulinaRefeicao;

    private Usuario usuario;

    public Registro() {
    }

    public Registro(long idRegistro, int registroGlicose, Date dataRegistro, Time horaRegistro,
                    String etiqueta, double insulinaCorrecao, double insulinaRefeicao, Usuario usuario) {
        this.idRegistro = idRegistro;
        this.registroGlicose = registroGlicose;
        this.dataRegistro = dataRegistro;
        this.horaRegistro = horaRegistro;
        this.etiqueta = etiqueta;
        this.insulinaCorrecao = insulinaCorrecao;
        this.insulinaRefeicao = insulinaRefeicao;
        this.usuario = usuario;
    }

    public long getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(long idRegistro) {
        this.idRegistro = idRegistro;
    }

    public int getRegistroGlicose() {
        return registroGlicose;
    }

    public void setRegistroGlicose(int registroGlicose) {
        this.registroGlicose = registroGlicose;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public Time getHoraRegistro() {
        return horaRegistro;
    }

    public void setHoraRegistro(Time horaRegistro) {
        this.horaRegistro = horaRegistro;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public double getInsulinaCorrecao() {
        return insulinaCorrecao;
    }

    public void setInsulinaCorrecao(double insulinaCorrecao) {
        this.insulinaCorrecao = insulinaCorrecao;
    }

    public double getInsulinaRefeicao() {
        return insulinaRefeicao;
    }

    public void setInsulinaRefeicao(double insulinaRefeicao) {
        this.insulinaRefeicao = insulinaRefeicao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Registro{" +
                "registroGlicose=" + registroGlicose +
                ", data_registro=" + dataRegistro +
                ", horaRegistro=" + horaRegistro +
                ", etiqueta='" + etiqueta + '\'' +
                ", insulinaCorrecao=" + insulinaCorrecao +
                ", insulinaFixa=" + insulinaRefeicao +
                '}';
    }
}
