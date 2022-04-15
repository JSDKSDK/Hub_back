package com.gs.exception.api;

import com.gs.util.Utils;
import org.json.JSONArray;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResponseException extends RuntimeException {

    private HttpStatus httpStatus;
    private String message;
    private String folio;
    private String info;
    private List<String> errors;

    public ResponseException(HttpStatus httpStatus, String message, String folio, String info, ArrayList<String> errors) {

        super(message);

        this.httpStatus = httpStatus;
        this.message = message;
        this.folio = folio;
        this.info = info;
        this.errors = errors;
    }

    public ResponseException(HttpStatus httpStatus, String message, String folio, String info, String error) {

        super(message);

        this.httpStatus = httpStatus;
        this.message = message;
        this.folio = folio;
        this.info = info;
        this.errors = Arrays.asList(error);
    }

    public ResponseException(HttpStatus httpStatus, String message, String folio, String info, StackTraceElement[] stackTraceElements) {

        super(message);

        this.httpStatus = httpStatus;
        this.message = message;
        this.folio = folio;
        this.info = info;
        this.errors.add(new JSONArray(stackTraceElements).toString());
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
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
}
