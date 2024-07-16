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
    private UserEntity creator;
    @OneToMany(fetch = FetchType.LAZY)
    private List<UserFriendGroupEntity> userFriendGroups;
    private String referralLink;
    @CreationTimestamp
    private LocalDateTime creationTime;

}