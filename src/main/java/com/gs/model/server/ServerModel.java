package com.gs.model.server;

import com.google.gson.annotations.SerializedName;
import com.gs.dto.server.ServerDetailDto;
import com.gs.helper.CurrentContext;
import com.gs.model.IModelAuditable;

import javax.validation.constraints.Pattern;
import java.util.Date;

public class ServerModel implements IModelAuditable {

    @SerializedName("IDSERVIDOR")
    private int idServidor;

    @SerializedName("IDAREA")
    private int idArea;

    @SerializedName("AREA")
    private String area;

    @SerializedName("IDTSERVIDOR")
    private int idTipoServidor;

    @SerializedName("TIPOSERVIDOR")
    private String tipoServidor;

    @SerializedName("IDSISOPE")
    private int  idSistemaOperativo;

    @SerializedName("SISTEMAOPERATIVO")
    private String  sistemaOperativo;

    @SerializedName("URL")
    private String url;

    @SerializedName("DESCRIPCION")
    private String descripcion;

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

    @SerializedName("IDDIRECCION")
    private int idDireccion;
    @SerializedName("DIRECCION")
    private String direccion;
    @SerializedName("IDNEGOCIO")
    private int idNegocio;
    @SerializedName("NEGOCIO")
    private String negocio;

    public int getIdServidor() {
        return idServidor;
    }

    public void setIdServidor(int idServidor) {
        this.idServidor = idServidor;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getIdTipoServidor() {
        return idTipoServidor;
    }

    public void setIdTipoServidor(int idTipoServidor) {
        this.idTipoServidor = idTipoServidor;
    }

    public String getTipoServidor() {
        return tipoServidor;
    }

    public void setTipoServidor(String tipoServidor) {
        this.tipoServidor = tipoServidor;
    }

    public int getIdSistemaOperativo() {
        return idSistemaOperativo;
    }

    public void setIdSistemaOperativo(int idSistemaOperativo) {
        this.idSistemaOperativo = idSistemaOperativo;
    }

    public String getSistemaOperativo() {
        return sistemaOperativo;
    }

    public void setSistemaOperativo(String sistemaOperativo) {
        this.sistemaOperativo = sistemaOperativo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    @Override
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public Date getFechaUltimaModificacion() {
        return fechaUltimaModificacion;
    }

    @Override
    public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
        this.fechaUltimaModificacion = fechaUltimaModificacion;
    }

    @Override
    public int getIdUsuarioCreacion() {
        return idUsuarioCreacion;
    }

    @Override
    public void setIdUsuarioCreacion(int idUsuarioCreacion) {
        this.idUsuarioCreacion = idUsuarioCreacion;
    }

    @Override
    public int getIdUsuarioUltimaModificacion() {
        return idUsuarioUltimaModificacion;
    }

    @Override
    public void setIdUsuarioUltimaModificacion(int idUsuarioUltimaModificacion) {
        this.idUsuarioUltimaModificacion = idUsuarioUltimaModificacion;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

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

    public static ServerModel dtoToModel(ServerDetailDto dto){

        ServerModel model = new ServerModel();

        model.setIdServidor(dto.getIdServidor());
        model.setUrl(dto.getUrl());
        model.setDescripcion(dto.getDescripcion());
        model.setEstatus(dto.getEstatus());
        model.setIdArea(dto.getIdArea());
        model.setArea(dto.getArea());
        model.setIdDireccion(dto.getIdDireccion());
        model.setDireccion(dto.getDireccion());
        model.setIdNegocio(dto.getIdNegocio());
        model.setNegocio(dto.getNegocio());
        model.setIdTipoServidor(dto.getIdTipoServidor());
        model.setTipoServidor(dto.getTipoServidor());
        model.setIdSistemaOperativo(dto.getIdSistemaOperativo());
        model.setSistemaOperativo(dto.getSistemaOperativo());

        CurrentContext.setCurrentUserToModel(model);

        return model;
    }
}
