package com.snapp.dangidongi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FriendGroupCreateModel {

    private String name;
    private String avatar;
    private String description;
    private Long creator;

}
