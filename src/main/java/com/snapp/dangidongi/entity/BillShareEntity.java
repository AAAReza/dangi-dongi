package com.snapp.dangidongi.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_BILL_SHARE")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BillShareEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private BigDecimal shareAmount;

    @ManyToOne
    @JoinColumn(name = "USER_FRIEND_GROUP_ID")
    private UserFriendGroupEntity userFriendGroup;

    @ManyToOne
    @JoinColumn(name = "BILL_ID")
    private BillEntity bill;
    @CreationTimestamp
    private LocalDateTime creationTime;


    @Transient
    public FriendGroupEntity getGroup() {
        return userFriendGroup.getGroup();
    }

    @Transient
    public UserEntity getUser() {
        return userFriendGroup.getUser();
    }
}
