package com.snapp.dangidongi.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_PAYMENT_INFO")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String trackId;
    private BigDecimal amount;
    private LocalDateTime paymentDate;

    @ManyToOne
    @JoinColumn(name = "BILL_SHARE_ID")
    private BillShareEntity billShare;

    @CreationTimestamp
    private LocalDateTime creationTime;

}
