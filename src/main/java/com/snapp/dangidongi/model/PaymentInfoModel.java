package com.snapp.dangidongi.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class PaymentInfoModel {

  private Long id;
  private String trackId;
  private BigDecimal amount;
  private LocalDateTime paymentDate;

  private BillShareModel billShare;

  private LocalDateTime creationTime;
}
