package com.gs.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StacktraceException {
    @JsonProperty("stackTraceElement")
    private StackTraceElement[] stackTraceElements;

    public StackTraceElement[] getElements() {
        return this.stackTraceElements;
    }

    public void setElements(StackTraceElement[] stackTraceElements) {
        this.stackTraceElements = stackTraceElements;
    }
}
