package com.snapp.dangidongi.configuration;

import com.snapp.dangidongi.common.ErrorModel;
import com.snapp.dangidongi.exception.DangiDongiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvisor {


    @ExceptionHandler(value = DangiDongiException.class)
    public ResponseEntity<ErrorModel> handleDangiDongiException(DangiDongiException ex) {
        ErrorModel errorResponse = ErrorModel.builder()
                .description(ex.getErrors().getDescription())
                .errorCode(ex.getErrors().getCode())
                .metadata(ex.getCause()).build();
        return new ResponseEntity<>(errorResponse, ex.getErrors().getStatus());
    }
}
