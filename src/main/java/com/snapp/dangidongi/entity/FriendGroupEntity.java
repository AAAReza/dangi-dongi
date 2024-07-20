package com.snapp.dangidongi.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "TB_FRIEND_GROUP")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FriendGroupEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;
  private String avatar;
  private String description;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "CREATOR_ID")
  private UserEntity creator;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "group")
  private List<UserFriendGroupEntity> userFriendGroups;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "friendGroup")
  private List<BillEntity> bills;

  private String referralLink;
  @CreationTimestamp private LocalDateTime creationTime;
}
