package com.snapp.dangidongi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserFriendGroupModel {

    private Long id;
    private UserModel user;
    private FriendGroupModel group;
    private LocalDateTime joinToGroupTime;
}
