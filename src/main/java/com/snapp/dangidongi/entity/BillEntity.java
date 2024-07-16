package com.snapp.dangidongi.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_BILL")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BillEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private BigDecimal amount;
    private String reason;
    private LocalDateTime paymentDate;

    @ManyToOne
    @JoinColumn(name = "BILL_OWNER_ID")
    private UserEntity billOwner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FRIEND_GROUP_ID")
    private FriendGroupEntity friendGroup;
    @CreationTimestamp
    private LocalDateTime creationTime;

}
