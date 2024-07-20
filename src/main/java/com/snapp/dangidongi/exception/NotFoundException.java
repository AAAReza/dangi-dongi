package com.snapp.dangidongi.exception;

import com.snapp.dangidongi.common.Errors;

public class NotFoundException extends DangiDongiException {
  public NotFoundException(String message) {
    super(Errors.RESOURCE_NOT_FOUND, message);
  }

  public NotFoundException() {
    super(Errors.RESOURCE_NOT_FOUND);
  }
}
