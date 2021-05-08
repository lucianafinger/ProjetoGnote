package com.example.projetognote.model;

public class Registro {
    private long idRegistro;
    private int registroGlicose;
    private String horaRegistro;
    private String etiqueta; // café, almoço
    private double insulinaCorrecao;
    private double insulinaRefeicao;

    public Registro() {
    }

    public Registro(long idRegistro, int registroGlicose, String horaRegistro, String etiqueta, double insulinaCorrecao, double insulinaRefeicao) {
        this.idRegistro = idRegistro;
        this.registroGlicose = registroGlicose;
        this.horaRegistro = horaRegistro;
        this.etiqueta = etiqueta;
        this.insulinaCorrecao = insulinaCorrecao;
        this.insulinaRefeicao = insulinaRefeicao;
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

    public String getHoraRegistro() {
        return horaRegistro;
    }

    public void setHoraRegistro(String horaRegistro) {
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
}
