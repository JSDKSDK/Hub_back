package com.gs.model.module;

import com.google.gson.annotations.SerializedName;
import com.gs.dto.servertype.module.ModuleDetailDto;
import com.gs.helper.CurrentContext;
import com.gs.model.IModelAuditable;

import java.util.Date;

public class ModuleModel implements IModelAuditable{

    @SerializedName("FIIDMODULO")
    private int idModulo;

    @SerializedName("NOMBRE")
    private String nombre;

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

    @SerializedName("PERMITIRASIGNACIONNIVEL")
    private int permitirAsignacionNivel;

    @SerializedName("ESTATUS")
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

    public static ModuleModel dtoToModel(ModuleDetailDto dto){

        ModuleModel model = new ModuleModel();

        model.setIdModulo(dto.getIdModulo());
        model.setNombre(dto.getNombre());
        model.setUrl(dto.getUrl());
        model.setPermitirAsignacionNivel(dto.getPermitirAsignacionNivel());
        model.setEstatus(dto.getEstatus());
        CurrentContext.setCurrentUserToModel(model);

        return model;

    }
}
