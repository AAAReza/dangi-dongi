package com.snapp.dangidongi.exception;

import com.snapp.dangidongi.common.Errors;

public class NotAllowedException extends DangiDongiException {


    public NotAllowedException() {
        super(Errors.NOT_ALLOWED);
    }
}
