package com.gs.dto.operatingSystem;

public class OperatingSystemDetailDto {

    private int idSOperativo;
    private String nombre;
    private int estatus;

    public int getIdSOperativo() {
        return idSOperativo;
    }

    public void setIdSOperativo(int idSOperativo) {
        this.idSOperativo = idSOperativo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }
}
