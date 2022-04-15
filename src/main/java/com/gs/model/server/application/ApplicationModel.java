package com.gs.model.server.application;

import com.google.gson.annotations.SerializedName;
import com.gs.dto.server.application.ApplicationDetailDto;
import com.gs.helper.CurrentContext;
import com.gs.model.IModelAuditable;

import java.util.Date;

public class ApplicationModel implements IModelAuditable {

    @SerializedName("FIIDSAPLICATIVO")
    private int idSAplicativo;

    @SerializedName("COMENTARIOS")
    private String comentarios;

    @SerializedName("URL")
    private String url;

    @SerializedName("FECHA_CREACION")
    private Date fechaCreacion;

    @SerializedName("ULTIMA_MODIFICACION")
    private Date fechaUltimaModificacion;

    @SerializedName("USUARIO_CREACION")
    private int idUsuarioCreacion;

    @SerializedName("USUARIO_MODIFICO")
    private int idUsuarioUltimaModificacion;

    @SerializedName("ESTATUS")
    private int estatus;

    @SerializedName("FIIDSERVIDOR")
    private int idServidor;

    @SerializedName("DESCRIPCION")
    private String descripcion;


    public int getIdSAplicativo() {
        return idSAplicativo;
    }

    public void setIdSAplicativo(int idSAplicativo) {
        this.idSAplicativo = idSAplicativo;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaUltimaModificacion() {
        return fechaUltimaModificacion;
    }

    public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
        this.fechaUltimaModificacion = fechaUltimaModificacion;
    }

    public int getIdUsuarioCreacion() {
        return idUsuarioCreacion;
    }

    public void setIdUsuarioCreacion(int idUsuarioCreacion) {
        this.idUsuarioCreacion = idUsuarioCreacion;
    }

    public int getIdUsuarioUltimaModificacion() {
        return idUsuarioUltimaModificacion;
    }

    public void setIdUsuarioUltimaModificacion(int idUsuarioUltimaModificacion) {
        this.idUsuarioUltimaModificacion = idUsuarioUltimaModificacion;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public int getIdServidor() {
        return idServidor;
    }

    public void setIdServidor(int idServidor) {
        this.idServidor = idServidor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public static ApplicationModel dtoToModel(ApplicationDetailDto dto){

        ApplicationModel model = new ApplicationModel();

        model.setIdSAplicativo(dto.getIdSAplicativo());
        model.setComentarios(dto.getComentarios());
        model.setUrl(dto.getUrl());
        model.setEstatus(dto.getEstatus());
        model.setIdServidor(dto.getIdServidor());
        model.setDescripcion(dto.getDescripcion());
        CurrentContext.setCurrentUserToModel(model);

        return model;

    }


}
