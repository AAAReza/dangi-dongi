package com.snapp.dangidongi.model;

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
public class BillShareModel {


    private Long id;
    private BigDecimal shareAmount;

    private UserModel user;
    private FriendGroupModel group;

    private BillModel bill;
    private LocalDateTime creationTime;

}
