package com.gs.dto.server.note;

public class ServerNoteEntryDto {

    private int idSNota;
    private int idServidor;
    private String titulo;
    private String nota;
    private String rutaArchivo;
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

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }
}
