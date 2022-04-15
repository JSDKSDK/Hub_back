package com.gs.model.server.note;

import com.google.gson.annotations.SerializedName;

public class ServerNoteDetailModel {

    @SerializedName("FIIDSNOTA")
    private int idSNota;

    @SerializedName("TITULO")
    private String titulo;

    @SerializedName("NOTA")
    private String nota;

    @SerializedName("RUTAARCHIVO")
    private String rutaArchivo;

    @SerializedName("ESTATUS")
    private int estatus;

    @SerializedName("IDSERVIDOR")
    private int idServidor;

    @SerializedName("DESCRIPCION")
    private String descripcion;

    @SerializedName("URL")
    private String url;

    public int getIdSNota() {
        return idSNota;
    }

    public void setIdSNota(int idSNota) {
        this.idSNota = idSNota;
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
}
