package com.gs.model.servertype;

import com.google.gson.annotations.SerializedName;
import com.gs.dto.business.BusinessDetailDto;
import com.gs.dto.servertype.ServerTypeDetailDto;
import com.gs.helper.CurrentContext;
import com.gs.model.business.BusinessModel;

import javax.websocket.server.ServerEndpoint;
import java.util.Date;

public class ServerTypeModel {

    @SerializedName("FIIDTIPOSERVIDOR")
    private int idTipoServidor;

    @SerializedName("NOMBRE")
    private String nombre;

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

    public int getIdTipoServidor() {
        return idTipoServidor;
    }

    public void setIdTipoServidor(int idTipoServidor) {
        this.idTipoServidor = idTipoServidor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public static ServerTypeModel dtoToModel(ServerTypeDetailDto dto) {

        ServerTypeModel model = new ServerTypeModel();

        model.setIdTipoServidor(dto.getIdTipoServidor());
        model.setNombre(dto.getNombre());
        model.setEstatus(dto.getEstatus());
        model.idUsuarioCreacion=1;
        model.idUsuarioUltimaModificacion=1;

        //CurrentContext.setCurrentUserToModel(model);
        return model;
    }
}
