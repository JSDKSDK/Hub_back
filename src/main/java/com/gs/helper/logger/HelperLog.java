package com.gs.helper.logger;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.gs.constants.SystemMessage;
import org.slf4j.Logger;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class HelperLog {

    @JsonProperty("log_data")
    @SerializedName("log_data")
    private LogData logData;

    private transient LocalDateTime dtStartTransaction;
    private transient LocalDateTime dtEndTransaction;
    private transient boolean hasError;

    public boolean hasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }


    public HelperLog() {

        this.logData = new LogData();
        this.startTransaction();
    }

    public LogData getLogData() {

        return this.logData;
    }

    public void startTransaction() {

        this.dtStartTransaction = LocalDateTime.now();
    }

    public void endTransaction(Logger logger) {

        this.dtEndTransaction = LocalDateTime.now();
        this.getLogData().setTiempoTotal(ChronoUnit.MILLIS.between(this.dtStartTransaction, this.dtEndTransaction));

        if (this.hasError()) {
            logger.error(this.toJson());
        } else {
            logger.info(this.toJson());
        }
    }

    public String getFecha() {

        return this.getLogData().getFecha();
    }

    public Long getTiempoTotal() {

        return this.getLogData().getTiempoTotal();
    }

    public String getMensaje() {

        return this.getLogData().getMensaje();
    }

    public void setMensaje(String mensaje) {

        this.getLogData().setMensaje(mensaje);
    }

    public List<Servicio> getServicios() {

        return this.getLogData().getServicios();
    }

    public void setServicios(List<Servicio> servicios) {

        this.getLogData().setServicios(servicios);
    }

    public void addServicio(Servicio servicio) {

        this.getLogData().getServicios().add(servicio);
        this.getLastService().startTransaction();
    }

    public Servicio getLastService() {

        return this.logData.getLastService();
    }

    public void setError(Throwable e) {

        this.setHasError(true);

        if (this.getLastService() != null) {
            this.getLastService().setStatus(SystemMessage.ERROR);
            this.getLastService().setStacktraceException(e.getStackTrace());
        }

        this.setMensaje(SystemMessage.ERROR + " " + e.getMessage());
    }

    public String toJson() {

        String json = new Gson().toJson(this);

        return json;
    }

    public String toString() {

        return "RootLog [log_data=" + this.logData + "]";
    }
}
