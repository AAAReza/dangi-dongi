package com.snapp.dangidongi.model;

import com.snapp.dangidongi.entity.Gender;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class UserModel {
    private Long id;
    @NotBlank
    private String username;
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    private String email;
    @NotBlank
    private String password;
    private Long phone;
    private Gender gender;
    private LocalDate birthday;
    private LocalDateTime creationTime;
}
