package com.gs.dto.servertype.module;

public class ModuleDetailDto {

    private int idModulo;
    private String nombre;

    private String url;

    private int permitirAsignacionNivel;

    private int estatus;


    public int getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(int idModulo) {
        this.idModulo = idModulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPermitirAsignacionNivel() {
        return permitirAsignacionNivel;
    }

    public void setPermitirAsignacionNivel(int permitirAsignacionNivel) {
        this.permitirAsignacionNivel = permitirAsignacionNivel;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }
}
