package com.gs.dto.servertype;

public class ServerTypeDetailDto {
    private int idTipoServidor;
    private String nombre;
    private int estatus;

    public int getIdTipoServidor() {
        return idTipoServidor;
    }

    public void setIdTipoServidor(int idTipoServidor) {
        this.idTipoServidor = idTipoServidor;
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
