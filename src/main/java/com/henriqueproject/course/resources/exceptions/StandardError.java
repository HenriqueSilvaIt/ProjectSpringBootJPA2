package com.henriqueproject.course.resources.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.Instant;

public class StandardError implements Serializable {
    private static final long serialVersionUID = 1L;

    //Formantando a data no JSON para ficar bonit
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    //Construtores
    public StandardError(){

    }
    public StandardError(Instant timestamp, Integer status, String error, String message, String path){
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    // G


    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    // Não precisa do Hash code equal porque em momento algum precisamos
    // compara um erro com outr
}
