package com.gs.dto.server.note;

import com.gs.dto.server.credential.ServerCredentialDetailDto;
import com.gs.model.server.credential.ServerCredentialDetailModel;
import com.gs.model.server.note.ServerNoteDetailModel;

public class ServerNoteDetailDto {

    private int idSNota;
    private String titulo;
    private String nota;
    private String rutaArchivo;
    private int estatus;
    private int idServidor;
    private String descripcion;
    private String url;

    public int getidSNota() {
        return idSNota;
    }

    public void setidSNota(int idSNota) {
        this.idSNota = idSNota;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNota() { return nota; }

    public void setNota(String nota) { this.nota = nota; }

    public String getRutaArchivo() { return rutaArchivo; }

    public void setRutaArchivo(String rutaArchivo) { this.rutaArchivo = rutaArchivo; }

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

    public static ServerNoteDetailDto serverNoteDetailModelToDto(ServerNoteDetailModel serverNoteDetailModel){

        ServerNoteDetailDto serverNoteDetailDto = new ServerNoteDetailDto();

        serverNoteDetailDto.setidSNota(serverNoteDetailModel.getIdSNota());
        serverNoteDetailDto.setTitulo(serverNoteDetailModel.getTitulo());
        serverNoteDetailDto.setNota(serverNoteDetailModel.getNota());
        serverNoteDetailDto.setRutaArchivo(serverNoteDetailModel.getRutaArchivo());
        serverNoteDetailDto.setEstatus(serverNoteDetailModel.getEstatus());
        serverNoteDetailDto.setIdServidor(serverNoteDetailModel.getIdServidor());
        serverNoteDetailDto.setDescripcion(serverNoteDetailModel.getDescripcion());
        serverNoteDetailDto.setUrl(serverNoteDetailModel.getUrl());

        return serverNoteDetailDto;
    }
}
