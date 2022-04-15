package com.gs.model.server.note;

import com.google.gson.annotations.SerializedName;
import com.gs.dto.server.note.ServerNoteEntryDto;
import com.gs.helper.CurrentContext;
import com.gs.model.IModelAuditable;

import java.util.Date;

public class ServerNoteModel implements IModelAuditable {

    @SerializedName("FIIDSNOTA")
    private int idSNota;

    @SerializedName("FIIDSERVIDOR")
    private int idServidor;

    @SerializedName("TITULO")
    private String titulo;

    @SerializedName("NOTA")
    private String nota;

    @SerializedName("RUTAARCHIVO")
    private String rutaArchivo;

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

    public int getIdSNota() {
        return idSNota;
    }

    public void setIdSNota(int idSNota) {
        this.idSNota = idSNota;
    }

    public int getIdServidor() {
        return idServidor;
    }

    public void setIdServidor(int idServidor) {
        this.idServidor = idServidor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) { this.rutaArchivo = rutaArchivo; }

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

    public static ServerNoteModel dtoToModel(ServerNoteEntryDto dto) {

        ServerNoteModel model = new ServerNoteModel();

        model.setIdSNota(dto.getIdSNota());
        model.setIdServidor(dto.getIdServidor());
        model.setTitulo(dto.getTitulo());
        model.setNota(dto.getNota());
        model.setRutaArchivo(dto.getRutaArchivo());
        model.setEstatus(dto.getEstatus());
        CurrentContext.setCurrentUserToModel(model);

        return model;
    }
}
