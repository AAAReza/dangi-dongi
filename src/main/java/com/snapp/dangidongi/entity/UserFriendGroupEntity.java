package com.snapp.dangidongi.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_USER_FRIEND_GROUP")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserFriendGroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserEntity user;
    @ManyToOne
    @JoinColumn(name = "GROUP_ID")
    private FriendGroupEntity group;
    @CreationTimestamp
    private LocalDateTime joinToGroupTime;
}
