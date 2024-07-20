package com.snapp.dangidongi.configuration;

import com.snapp.dangidongi.common.ErrorModel;
import com.snapp.dangidongi.common.Errors;
import com.snapp.dangidongi.exception.DangiDongiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionAdvisor {

  @ExceptionHandler(value = DangiDongiException.class)
  public ResponseEntity<ErrorModel> handleDangiDongiException(DangiDongiException ex) {
    ErrorModel errorResponse =
        ErrorModel.builder()
            .description(ex.getErrors().getDescription())
            .errorCode(ex.getErrors().getCode())
            .metadata(ex.getMessage())
            .build();
    return new ResponseEntity<>(errorResponse, ex.getErrors().getStatus());
  }

  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorModel> handleConstraintViolationException(
      MethodArgumentNotValidException ex) {
    ErrorModel errorModel =
        ErrorModel.builder()
            .description(Errors.INVALID_REQUEST.getDescription())
            .metadata(
                ex.getBindingResult().getFieldErrors().stream()
                    .map(
                        fieldError ->
                            "[" + fieldError.getField() + "] : " + fieldError.getDefaultMessage())
                    .collect(Collectors.joining(", ")))
            .errorCode(Errors.INVALID_REQUEST.getCode())
            .build();
    return new ResponseEntity<ErrorModel>(errorModel, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
  public ResponseEntity<ErrorModel> handleSQLIntegrityConstraintViolationException(
      SQLIntegrityConstraintViolationException ex) {
    ErrorModel errorModel =
        ErrorModel.builder()
            .description(Errors.INVALID_REQUEST.getDescription())
            .metadata(ex.getMessage())
            .errorCode(Errors.INVALID_REQUEST.getCode())
            .build();
    return new ResponseEntity<ErrorModel>(errorModel, HttpStatus.BAD_REQUEST);
  }
}
