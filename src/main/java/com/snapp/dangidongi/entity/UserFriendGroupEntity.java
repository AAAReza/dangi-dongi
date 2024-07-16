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
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;
    @ManyToOne(fetch = FetchType.LAZY)
    private FriendGroupEntity group;
    @CreationTimestamp
    private LocalDateTime joinToGroupTime;
}
