package com.gs.dto.server.application;

import com.gs.helper.CurrentContext;
import com.gs.model.server.application.ApplicationModel;

public class ApplicationDetailDto {

    private int idSAplicativo;

    private int idServidor;

    private String url;

    private String comentarios;

    private int estatus;

    private String descripcion;

    public int getIdSAplicativo() {
        return idSAplicativo;
    }

    public void setIdSAplicativo(int idSAplicativo) {
        this.idSAplicativo = idSAplicativo;
    }

    public int getIdServidor() {
        return idServidor;
    }

    public void setIdServidor(int idServidor) {
        this.idServidor = idServidor;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
