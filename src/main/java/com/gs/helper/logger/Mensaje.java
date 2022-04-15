package com.gs.helper.logger;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gs.helper.logger.Servicio;

import java.util.List;

public class Mensaje {
    @JsonProperty("serviciosError")
    private List<Servicio> serviciosError;

    public List<Servicio> getServiciosError() {
        return this.serviciosError;
    }

    public void setServiciosError(List<Servicio> serviciosError) {
        this.serviciosError = serviciosError;
    }

    public String toString() {
        return "Mensaje [serviciosError=" + this.serviciosError + "]";
    }
}
