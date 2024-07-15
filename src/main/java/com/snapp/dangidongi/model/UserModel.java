package com.snapp.dangidongi.model;

import com.snapp.dangidongi.entity.Gender;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class UserModel {
    private Long id;
    @NotNull
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    @NotNull
    private String password;
    private Long phone;
    private Gender gender;
    private LocalDate birthday;
    private LocalDateTime creationTime;
}
