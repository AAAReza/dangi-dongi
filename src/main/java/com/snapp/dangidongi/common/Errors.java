package com.snapp.dangidongi.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum Errors {


    INTERNAL_SERVER_ERROR("0001", HttpStatus.INTERNAL_SERVER_ERROR, "Sorry and error occur on server, we fixed it as soon as possible"),
    RESOURCE_NOT_FOUND("0002", HttpStatus.NOT_FOUND, "The requested resource was not found : "),
    INVALID_REQUEST("0003", HttpStatus.BAD_REQUEST, "The request is invalid"),
    NOT_ALLOWED("0004", HttpStatus.BAD_REQUEST, "Cannot accept your request"),
    ;


    private final String code;
    private final HttpStatus status;
    private final String description;

    Errors(String code, HttpStatus status, String description) {
        this.code = code;
        this.status = status;
        this.description = description;
    }


}
