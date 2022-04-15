package com.gs.model.user;

import com.google.gson.annotations.SerializedName;
import com.gs.model.IModelAuditable;

import java.util.Date;

public class UserModel implements IModelAuditable {

    @SerializedName("FIIDUSUARIO")
    private int idusuario;

    @SerializedName("NOMBRE")
    private String nombre;

    @SerializedName("APPATERNO")
    private String apellidoPaterno;

    @SerializedName("APMATERNO")
    private String apellidoMaterno;

    @SerializedName("NUMEMPLEADO")
    private String numeroEmpleado;

    @SerializedName("CONTRASENIA")
    private String contrasenia;

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

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getNumeroEmpleado() {
        return numeroEmpleado;
    }

    public void setNumeroEmpleado(String numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
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
}
