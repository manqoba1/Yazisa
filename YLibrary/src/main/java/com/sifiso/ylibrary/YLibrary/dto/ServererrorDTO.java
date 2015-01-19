package com.sifiso.ylibrary.YLibrary.dto;

import java.io.Serializable;

/**
 * Created by CodeTribe1 on 2014-11-22.
 */
public class ServererrorDTO implements Serializable {

    private Integer serverErrorID;
    private Integer statusCode;
    private String message;
    private long dateOccured;
    private String origin;

    public Integer getServerErrorID() {
        return serverErrorID;
    }

    public void setServerErrorID(Integer serverErrorID) {
        this.serverErrorID = serverErrorID;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getDateOccured() {
        return dateOccured;
    }

    public void setDateOccured(long dateOccured) {
        this.dateOccured = dateOccured;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
