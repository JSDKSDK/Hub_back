package com.gs.model.server.credential;

import com.google.gson.annotations.SerializedName;

public class ServerCredentialDetailModel {
    @SerializedName("FIIDSCREDENCIAL")
    private int idSCredencial;

    @SerializedName("USUARIO")
    private String usuario;

    @SerializedName("CONTRASENIA")
    private String contrasena;

    @SerializedName("COMENTARIO")
    private String comentario;

    @SerializedName("ESTATUS")
    private int estatus;

    @SerializedName("FIIDSERVIDOR")
    private int idServidor;

    @SerializedName("DESCRIPCION")
    private String descripcion;

    @SerializedName("URL")
    private String url;

    @SerializedName("FIIDTCONTRASENIA")
    private int idTContrasena;

    @SerializedName("TIPOCONTRASENIA")
    private String tipoContrasena;

    public int getIdSCredencial() {
        return idSCredencial;
    }

    public void setIdSCredencial(int idSCredencial) {
        this.idSCredencial = idSCredencial;
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

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getComentario() { return comentario; }

    public void setComentario(String comentario) { this.comentario = comentario; }

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getIdTContrasena() {
        return idTContrasena;
    }

    public void setIdTContrasena(int idTContrasena) {
        this.idTContrasena = idTContrasena;
    }

    public String getTipoContrasena() {
        return tipoContrasena;
    }

    public void setTipoContrasena(String tipoContrasena) {
        this.tipoContrasena = tipoContrasena;
    }
}
