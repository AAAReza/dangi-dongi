package com.snapp.dangidongi.exception;

import com.snapp.dangidongi.common.Errors;
import lombok.Getter;

@Getter
public abstract class DangiDongiException extends Throwable {


    private final Errors errors;
    private String message;

    public DangiDongiException(Errors errors, Throwable cause) {
        super(errors.getDescription(), cause);
        this.errors = errors;
    }

    public DangiDongiException(Errors errors, String message) {
        super(errors.getDescription(), null);
        this.errors = errors;
        this.message = message;
    }

    public DangiDongiException(Errors errors) {
        super(errors.getDescription(), null);
        this.errors = errors;
    }

}
