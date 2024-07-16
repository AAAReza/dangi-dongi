package com.snapp.dangidongi.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BillModel {

    private Long id;
    @NotBlank
    private String name;
    @NotNull
    private BigDecimal amount;
    private String reason;
    private LocalDateTime paymentDate;
    @NotNull
    private UserModel billOwner;
    @NotNull
    private FriendGroupModel friendGroup;
    private LocalDateTime creationTime;

}
