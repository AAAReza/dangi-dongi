package com.snapp.dangidongi.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "TB_USER")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Long phone;
    private Gender gender;
    private LocalDate birthday;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<UserFriendGroupEntity> userFriendGroups;
    @CreationTimestamp
    private LocalDateTime creationTime;

}
