package com.snapp.dangidongi.exception;

import com.snapp.dangidongi.common.Errors;

public class NotFoundException extends DangiDongiException {
    public NotFoundException() {
        super(Errors.RESOURCE_NOT_FOUND, null);
    }
}
