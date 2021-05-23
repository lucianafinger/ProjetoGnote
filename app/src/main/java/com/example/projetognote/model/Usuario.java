package com.example.projetognote.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Serializable, Parcelable {
    private long idUsuario;
    private String nome, email, senha;
    private int correcaoHgt, hipoglicemia, hiperglicemia, idealMinima, idealMaxima, intervalo;
    private double insulina;

    private List<Registro> registros = new ArrayList<Registro>();

    public Usuario() {
    }

    public Usuario(long idUsuario, String nome, String email, String senha,
                   int correcaoHgt, int hipoglicemia, int hiperglicemia, int idealMinima, int idealMaxima,
                   int intervalo, double insulina, List<Registro> registros) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.correcaoHgt = correcaoHgt;
        this.hipoglicemia = hipoglicemia;
        this.hiperglicemia = hiperglicemia;
        this.idealMinima = idealMinima;
        this.idealMaxima = idealMaxima;
        this.intervalo = intervalo;
        this.insulina = insulina;
        this.registros = registros;
    }

    protected Usuario(Parcel in) {
        idUsuario = in.readLong();
        nome = in.readString();
        email = in.readString();
        senha = in.readString();
        correcaoHgt = in.readInt();
        hipoglicemia = in.readInt();
        hiperglicemia = in.readInt();
        idealMinima = in.readInt();
        idealMaxima = in.readInt();
        intervalo = in.readInt();
        insulina = in.readDouble();
    }

    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getCorrecaoHgt() {
        return correcaoHgt;
    }

    public void setCorrecaoHgt(int correcaoHgt) {
        this.correcaoHgt = correcaoHgt;
    }

    public int getHipoglicemia() {
        return hipoglicemia;
    }

    public void setHipoglicemia(int hipoglicemia) {
        this.hipoglicemia = hipoglicemia;
    }

    public int getHiperglicemia() {
        return hiperglicemia;
    }

    public void setHiperglicemia(int hiperglicemia) {
        this.hiperglicemia = hiperglicemia;
    }

    public int getIdealMinima() {
        return idealMinima;
    }

    public void setIdealMinima(int idealMinima) {
        this.idealMinima = idealMinima;
    }

    public int getIdealMaxima() {
        return idealMaxima;
    }

    public void setIdealMaxima(int idealMaxima) {
        this.idealMaxima = idealMaxima;
    }

    public int getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(int intervalo) {
        this.intervalo = intervalo;
    }

    public double getInsulina() {
        return insulina;
    }

    public void setInsulina(double insulina) {
        this.insulina = insulina;
    }

    public List<Registro> getRegistros() {
        return registros;
    }

    public void setRegistros(List<Registro> registros) {
        this.registros = registros;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(idUsuario);
        dest.writeString(nome);
        dest.writeString(email);
        dest.writeString(senha);
        dest.writeInt(correcaoHgt);
        dest.writeInt(hipoglicemia);
        dest.writeInt(hiperglicemia);
        dest.writeInt(idealMinima);
        dest.writeInt(idealMaxima);
        dest.writeInt(intervalo);
        dest.writeDouble(insulina);
    }
    @Override
    public int describeContents() {
        return 0;
    }

}


