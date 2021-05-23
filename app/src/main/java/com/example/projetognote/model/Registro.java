package com.example.projetognote.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalTime;

public class Registro  implements Serializable, Parcelable {
    private long idRegistro;
    private int registroGlicose;
    private Date data_registro;
//    private LocalTime horaRegistro;
    private String etiqueta;
    private double insulinaCorrecao, insulinaRefeicao;

    private Usuario usuario;

    public Registro() {
    }

    public Registro(long idRegistro, int registroGlicose, Date dataRegistro,
                    String etiqueta, double insulinaCorrecao, double insulinaRefeicao, Usuario usuario) {
        this.idRegistro = idRegistro;
        this.registroGlicose = registroGlicose;
        this.data_registro = dataRegistro;
//        this.horaRegistro = horaRegistro;
        this.etiqueta = etiqueta;
        this.insulinaCorrecao = insulinaCorrecao;
        this.insulinaRefeicao = insulinaRefeicao;
        this.usuario = usuario;
    }

    protected Registro(Parcel in) {
        idRegistro = in.readLong();
        registroGlicose = in.readInt();
        etiqueta = in.readString();
        insulinaCorrecao = in.readDouble();
        insulinaRefeicao = in.readDouble();
        usuario = in.readParcelable(Usuario.class.getClassLoader());
    }

    public static final Creator<Registro> CREATOR = new Creator<Registro>() {
        @Override
        public Registro createFromParcel(Parcel in) {
            return new Registro(in);
        }

        @Override
        public Registro[] newArray(int size) {
            return new Registro[size];
        }
    };

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

    public Date getData_registro() {
        return data_registro;
    }

    public void setData_registro(Date data_registro) {
        this.data_registro = data_registro;
    }

//    public LocalTime getHoraRegistro() {
//        return horaRegistro;
//    }
//
//    public void setHoraRegistro(LocalTime horaRegistro) {
//        this.horaRegistro = horaRegistro;
//    }

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
                ", data_registro=" + data_registro +
//                ", horaRegistro=" + horaRegistro +
                ", etiqueta='" + etiqueta + '\'' +
                ", insulinaCorrecao=" + insulinaCorrecao +
                ", insulinaFixa=" + insulinaRefeicao +
                "usuario= " + usuario.toString() +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(idRegistro);
        dest.writeInt(registroGlicose);
        dest.writeString(etiqueta);
        dest.writeDouble(insulinaCorrecao);
        dest.writeDouble(insulinaRefeicao);
        dest.writeParcelable(usuario, flags);
    }
}