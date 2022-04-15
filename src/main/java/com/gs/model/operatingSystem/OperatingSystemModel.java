package com.gs.model.operatingSystem;

import com.google.gson.annotations.SerializedName;
import com.gs.dto.business.BusinessDetailDto;
import com.gs.dto.operatingSystem.OperatingSystemDetailDto;
import com.gs.model.business.BusinessModel;

import java.util.Date;

public class OperatingSystemModel {

    @SerializedName("FIIDSOPERATIVO")
    private int idSOperativo;

    @SerializedName("NOMBRE")
    private String nombre;

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

    public static OperatingSystemModel dtoToModel(OperatingSystemDetailDto dto) {

        OperatingSystemModel model = new OperatingSystemModel();

        model.setIdSOperativo(dto.getIdSOperativo());
        model.setNombre(dto.getNombre());
        model.setEstatus(dto.getEstatus());
        model.idUsuarioCreacion=1;
        model.idUsuarioUltimaModificacion=1;
        return model;
    }
}
