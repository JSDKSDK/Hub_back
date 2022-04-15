package com.gs.helper.logger;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.JsonElement;
import com.gs.exception.StacktraceException;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Servicio {

    @JsonProperty("servicio")
    private String servicio;

    @JsonProperty("sistema")
    private String sistema;

    @JsonProperty("status")
    private String status;

    @JsonProperty("tiempo")
    private Long tiempo;

    @JsonProperty("tipoTrans")
    private String tipoTrans;

    @JsonProperty("mensajeError")
    private String mensajeError;

    @JsonProperty("request")
    private JsonElement request;

    @JsonProperty("response")
    private JsonElement response;

    @JsonProperty("stacktraceException")
    private StacktraceException stacktraceException;

    private static final String SISTEMA = "Hub QA";
    private transient LocalDateTime dtStartTransaction;
    private transient LocalDateTime dtEndTransaction;

    public Servicio() {

        this.sistema = SISTEMA;
    }

    public Servicio(String servicio, String tipoTrans) {

        this.sistema = SISTEMA;
        this.servicio = servicio;
        this.tipoTrans = tipoTrans;
    }

    public void startTransaction(){

        this.dtStartTransaction = LocalDateTime.now();
    }

    public void endTransaction(){

        this.dtEndTransaction = LocalDateTime.now();
        this.setTiempo(ChronoUnit.MILLIS.between(this.dtStartTransaction, this.dtEndTransaction));
    }

    public JsonElement getResponse() {
        return this.response;
    }

    public void setResponse(JsonElement response) {
        this.response = response;
    }

    public JsonElement getRequest() {
        return this.request;
    }

    public void setRequest(JsonElement request) {
        this.request = request;
    }

    public String getServicio() {
        return this.servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getSistema() {
        return this.sistema;
    }

    public void setSistema(String sistema) {
        this.sistema = sistema;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getTiempo() {
        return this.tiempo;
    }

    public void setTiempo(Long tiempo) {
        this.tiempo = tiempo;
    }

    public String getTipoTrans() {
        return this.tipoTrans;
    }

    public void setTipoTrans(String tipoTrans) {
        this.tipoTrans = tipoTrans;
    }

    public String getMensajeError() {
        return this.mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public StacktraceException getStacktraceException() {
        return this.stacktraceException;
    }

    public void setStacktraceException(StacktraceException stacktraceException) {
        this.stacktraceException = stacktraceException;
    }

    public void setStacktraceException(StackTraceElement[] stackTraceElements) {

        StacktraceException stacktraceException = new StacktraceException();

        stacktraceException.setElements(stackTraceElements);

        this.stacktraceException = stacktraceException;
    }

    public String toString() {
        return "Servicio [servicio=" + this.servicio + ", sistema=" + this.sistema + ", status=" + this.status + ", tiempo=" + this.tiempo + ", tipoTrans=" + this.tipoTrans + ", mensajeError=" + this.mensajeError + ", request=" + this.request + ", response=" + this.response + "]";
    }
}