package com.gs.model.direction;

import com.google.gson.annotations.SerializedName;
import com.gs.dto.business.BusinessDetailDto;
import com.gs.dto.direction.DirectionEntryDto;
import com.gs.helper.CurrentContext;
import com.gs.model.IModelAuditable;

import java.util.Date;

public class DirectionModel implements IModelAuditable {

    @SerializedName("FIIDDIRECCION")
    private int idDireccion;

    @SerializedName("FIIDNEGOCIO")
    private int idNegocio;

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

    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public int getIdNegocio() {
        return idNegocio;
    }

    public void setIdNegocio(int idNegocio) {
        this.idNegocio = idNegocio;
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

    public static DirectionModel dtoToModel(DirectionEntryDto dto) {

        DirectionModel model = new DirectionModel();

        model.setIdDireccion(dto.getIdDireccion());
        model.setIdNegocio(dto.getIdNegocio());
        model.setNombre(dto.getNombre());
        model.setEstatus(dto.getEstatus());
        CurrentContext.setCurrentUserToModel(model);

        return model;
    }
}
