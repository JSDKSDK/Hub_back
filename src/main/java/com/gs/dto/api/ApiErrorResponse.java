package com.gs.dto.api;

import com.gs.util.DataUtil;

import java.util.Arrays;
import java.util.List;

public class ApiErrorResponse {

    private int httpStatus;
    private String message;
    private String folio;
    private String info;
    private List<String> errors;

    public ApiErrorResponse(int httpStatus, String message, String folio, String info, List<String> errors) {

        this.httpStatus = httpStatus;
        this.message = message;
        this.folio = folio;
        this.info = info;
        this.errors = errors;
    }

    public ApiErrorResponse(int httpStatus, String message, String folio, String info, String error) {

        this.httpStatus = httpStatus;
        this.message = message;
        this.folio = folio;
        this.info = info;
        this.errors = Arrays.asList(error);
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public String toJson(){

        return DataUtil.objectToJsonString(this);
    }
}
