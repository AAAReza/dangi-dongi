package com.snapp.dangidongi.configuration;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@Component
public class DangiDongiErrorAttribute extends DefaultErrorAttributes {

  @Override
  public Map<String, Object> getErrorAttributes(
      WebRequest webRequest, ErrorAttributeOptions options) {
    Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);
    errorAttributes.put("errorCode", "0000");
    errorAttributes.put("description", errorAttributes.get("error"));
    HashMap<String, Object> metadata = new HashMap<>();
    metadata.put("path", errorAttributes.get("path"));
    metadata.put("trace", errorAttributes.get("trace"));
    metadata.put("exception", errorAttributes.get("exception"));
    errorAttributes.put("metadata", metadata);
    errorAttributes.remove("error");
    errorAttributes.remove("errors");
    errorAttributes.remove("timestamp");
    errorAttributes.remove("status");
    errorAttributes.remove("path");
    errorAttributes.remove("exception");
    return errorAttributes;
  }
}
