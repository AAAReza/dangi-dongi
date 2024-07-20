package com.snapp.dangidongi.model;

import com.snapp.dangidongi.entity.Gender;
import com.snapp.dangidongi.security.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class UserModel {
  private Long id;
  @NotBlank private String username;
  @NotBlank private String firstname;
  @NotBlank private String lastname;
  private String email;
  private List<FriendGroupModel> friendGroups;
  @NotBlank private String password;
  private Long phone;
  private Gender gender;
  @NotNull private Role role;
  private LocalDate birthday;
  private LocalDateTime creationTime;
}
