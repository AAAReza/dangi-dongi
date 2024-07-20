package com.snapp.dangidongi.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

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

  @CreationTimestamp private LocalDateTime creationTime;

  @Transient
  public FriendGroupEntity getGroup() {
    if (Objects.nonNull(userFriendGroup)) {
      return userFriendGroup.getGroup();
    }
    return null;
  }

  @Transient
  public UserEntity getUser() {
    if (Objects.nonNull(userFriendGroup)) {
      return userFriendGroup.getUser();
    }
    return null;
  }
}
