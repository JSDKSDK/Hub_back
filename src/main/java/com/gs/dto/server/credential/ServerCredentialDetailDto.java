package com.gs.dto.server.credential;

import com.gs.model.server.credential.ServerCredentialDetailModel;

public class ServerCredentialDetailDto {


    private int idSCredencial;
    private String usuario;
    private String contrasena;
    private String comentario;
    private int estatus;
    private int idServidor;
    private String descripcion;
    private String url;
    private int idTContrasena;
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

    public static ServerCredentialDetailDto serverCredentialDetailModelToDto(ServerCredentialDetailModel serverCredentialDetailModel){

        ServerCredentialDetailDto serverCredentialDetailDto = new ServerCredentialDetailDto();

        serverCredentialDetailDto.setIdSCredencial(serverCredentialDetailModel.getIdSCredencial());
        serverCredentialDetailDto.setUsuario(serverCredentialDetailModel.getUsuario());
        serverCredentialDetailDto.setContrasena(serverCredentialDetailModel.getContrasena());
        serverCredentialDetailDto.setComentario(serverCredentialDetailModel.getComentario());
        serverCredentialDetailDto.setEstatus(serverCredentialDetailModel.getEstatus());
        serverCredentialDetailDto.setIdServidor(serverCredentialDetailModel.getIdServidor());
        serverCredentialDetailDto.setDescripcion(serverCredentialDetailModel.getDescripcion());
        serverCredentialDetailDto.setUrl(serverCredentialDetailModel.getUrl());
        serverCredentialDetailDto.setIdTContrasena(serverCredentialDetailModel.getIdTContrasena());
        serverCredentialDetailDto.setTipoContrasena(serverCredentialDetailModel.getTipoContrasena());

        return serverCredentialDetailDto;
    }
}
