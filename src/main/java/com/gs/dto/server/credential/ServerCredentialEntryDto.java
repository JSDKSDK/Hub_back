package com.gs.dto.server.credential;

public class ServerCredentialEntryDto {

    private int idSCredencial;
    private int idServidor;
    private int idTContrasena;
    private String usuario;
    private String contrasena;
    private String comentario;
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

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getComentario() { return comentario; }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }
}
