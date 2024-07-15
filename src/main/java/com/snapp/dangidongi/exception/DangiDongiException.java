package com.snapp.dangidongi.exception;

import com.snapp.dangidongi.common.Errors;
import lombok.Getter;

@Getter
public abstract class DangiDongiException extends Throwable {


    private final Errors errors;

    public DangiDongiException(Errors errors, Throwable cause) {
        super(errors.getDescription(), cause);
        this.errors = errors;
    }


}
