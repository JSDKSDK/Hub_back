package com.gs.model.passwordType;

import com.google.gson.annotations.SerializedName;
import com.gs.dto.passwordType.PasswordTypeDetailDto;
import com.gs.helper.CurrentContext;
import com.gs.model.IModelAuditable;
import java.util.Date;

public class PasswordTypeModel implements IModelAuditable {

    @SerializedName("FIIDTCONTRASENIA")
    private int idPasswordType;

    @SerializedName("TIPOCONTRASENIA")
    private String passwordType;

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

    public int getIdPasswordType() {
        return idPasswordType;
    }

    public void setIdPasswordType(int idPasswordType) {
        this.idPasswordType = idPasswordType;
    }

    public String getPasswordType() {
        return passwordType;
    }

    public void setPasswordType(String passwordType) {
        this.passwordType = passwordType;
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

    public static PasswordTypeModel dtoToModel(PasswordTypeDetailDto dto) {

        PasswordTypeModel model = new PasswordTypeModel();

        model.setIdPasswordType(dto.getIdPasswordType());
        model.setPasswordType(dto.getPasswordType());
        model.setEstatus(dto.getEstatus());
        CurrentContext.setCurrentUserToModel(model);
        return model;
    }
}
