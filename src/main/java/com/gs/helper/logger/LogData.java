package com.gs.helper.logger;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.gs.util.Utils;

import java.util.ArrayList;
import java.util.List;

class LogData {
    @JsonProperty("fecha")
    private String fecha;

    @JsonProperty("Mensaje")
    private String Mensaje;

    @JsonProperty("TiempoTotal")
    private Long TiempoTotal;

    @JsonProperty("servicios")
    private List<Servicio> servicios;

    public LogData() {

        this.setFecha(Utils.getTimeStampLogData());
        this.setServicios(new ArrayList<Servicio>());
    }

    public String getFecha() {

        return this.fecha;
    }

    public void setFecha(String fecha) {

        this.fecha = fecha;
    }

    public Long getTiempoTotal() {

        return this.TiempoTotal;
    }

    public void setTiempoTotal(Long tiempoTotal) {

        this.TiempoTotal = tiempoTotal;
    }

    public String getMensaje() {

        return this.Mensaje;
    }

    public void setMensaje(String mensaje) {

        this.Mensaje = mensaje;
    }

    public List<Servicio> getServicios() {

        return this.servicios;
    }

    public void setServicios(List<Servicio> servicios) {

        this.servicios = servicios;
    }

    public Servicio getLastService() {

        Servicio service = null;

        if (this.servicios.size() > 0) {
            service = this.servicios.get(this.servicios.size() - 1);
        }

        return service;
    }

    public String toString() {
        return "[fecha:" + this.fecha + ", Mensaje:" + this.Mensaje + ", TiempoTotal:" + this.TiempoTotal
                + ", servicios:" + this.servicios + "]";
    }
}
