package com.gs.model.server.credential;

import com.google.gson.annotations.SerializedName;
import com.gs.dto.server.credential.ServerCredentialEntryDto;
import com.gs.helper.CurrentContext;
import com.gs.model.IModelAuditable;

import java.util.Date;

public class ServerCredentialModel implements IModelAuditable {

    @SerializedName("FIIDSCREDENCIAL")
    private int idSCredencial;

    @SerializedName("FIIDSERVIDOR")
    private int idServidor;

    @SerializedName("FIIDTCONTRASENIA")
    private int idTContrasena;

    @SerializedName("USUARIO")
    private String usuario;

    @SerializedName("CONTRASENIA")
    private String contrasena;

    @SerializedName("COMENTARIO")
    private String comentario;

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

    public int getIdSCredencial() {
        return idSCredencial;
    }

    public void setIdSCredencial(int idSCredencial) {
        this.idSCredencial = idSCredencial;
    }

    public int getIdServidor() {
        return idServidor;
    }

    public void setIdServidor(int idServidor) {
        this.idServidor = idServidor;
    }

    public int getIdTContrasena() {
        return idTContrasena;
    }

    public void setIdTContrasena(int idTContrasena) {
        this.idTContrasena = idTContrasena;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public String getComentario() { return comentario; }

    public void setComentario(String comentario) { this.comentario = comentario; }

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

    public static ServerCredentialModel dtoToModel(ServerCredentialEntryDto dto) {

        ServerCredentialModel model = new ServerCredentialModel();

        model.setIdSCredencial(dto.getIdSCredencial());
        model.setIdServidor(dto.getIdServidor());
        model.setIdTContrasena(dto.getIdTContrasena());
        model.setUsuario(dto.getUsuario());
        model.setContrasena(dto.getContrasena());
        model.setComentario(dto.getComentario());
        model.setEstatus(dto.getEstatus());
        CurrentContext.setCurrentUserToModel(model);

        return model;
    }
}
