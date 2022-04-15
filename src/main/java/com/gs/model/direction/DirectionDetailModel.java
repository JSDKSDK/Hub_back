package com.gs.model.direction;

import com.google.gson.annotations.SerializedName;

public class DirectionDetailModel {

    @SerializedName("FIIDDIRECCION")
    private int idDireccion;

    @SerializedName("DIRECCION")
    private String direccion;

    @SerializedName("ESTATUS")
    private int estatus;

    @SerializedName("FIIDNEGOCIO")
    private int idNegocio;

    @SerializedName("NEGOCIO")
    private String negocio;

    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public int getIdNegocio() {
        return idNegocio;
    }

    public void setIdNegocio(int idNegocio) {
        this.idNegocio = idNegocio;
    }

    public String getNegocio() {
        return negocio;
    }

    public void setNegocio(String negocio) {
        this.negocio = negocio;
    }
}
