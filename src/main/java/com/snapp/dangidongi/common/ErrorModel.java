package com.snapp.dangidongi.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorModel {

  private String errorCode;
  private String description;
  private Object metadata;
}
