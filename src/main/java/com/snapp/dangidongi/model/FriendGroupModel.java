package com.snapp.dangidongi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FriendGroupModel {

    private Long id;
    private String name;
    private String avatar;
    private String description;
    private UserModel creator;
    private List<UserFriendGroupModel> userFriendGroups;
    private String referralLink;
    private LocalDateTime creationTime;

}
