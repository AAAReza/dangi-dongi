package com.snapp.dangidongi.service;

import com.snapp.dangidongi.entity.BillEntity;
import com.snapp.dangidongi.entity.BillShareEntity;
import com.snapp.dangidongi.entity.UserEntity;
import com.snapp.dangidongi.exception.NotFoundException;
import com.snapp.dangidongi.mapper.BillShareMapper;
import com.snapp.dangidongi.model.BillShareModel;
import com.snapp.dangidongi.repository.BillShareRepository;
import com.snapp.dangidongi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@AllArgsConstructor
public class BillShareService {


    private final BillShareRepository billShareRepository;
    private final BillShareMapper billShareMapper;
    private final UserRepository userRepository;


    public void calculateBillShareInGroup(BillEntity bill, Long groupFriendId) throws NotFoundException {
        Page<UserEntity> usersInGroup = userRepository.findByUserFriendGroups_Group_Id(groupFriendId, Pageable.unpaged());
        BigDecimal shareOfAnyOne = bill.getAmount().divide(new BigDecimal(usersInGroup.getTotalElements()), 0, RoundingMode.FLOOR);
        BigDecimal residueOfAmount = bill.getAmount();
        do {
            for (UserEntity userEntity : usersInGroup.getContent()) {
                BillShareEntity billShareEntity = BillShareEntity.builder()
                        .bill(bill)
                        .userFriendGroup(userEntity.getUserFriendGroups()
                                .stream()
                                .filter(userFriendGroupEntity -> userFriendGroupEntity.getGroup()
                                        .getId().equals(bill.getFriendGroup().getId()))
                                .findFirst().get())
                        .build();
                if (residueOfAmount.subtract(shareOfAnyOne).compareTo(shareOfAnyOne) > 0) {
                    billShareEntity.setShareAmount(shareOfAnyOne);
                    residueOfAmount = residueOfAmount.subtract(shareOfAnyOne);
                } else {
                    billShareEntity.setShareAmount(residueOfAmount);
                }
                billShareRepository.save(billShareEntity);
            }
        } while (usersInGroup.hasNext());
    }


    public BillShareModel findById(Long id) throws NotFoundException {
        BillShareEntity billShare = billShareRepository.findById(id).orElseThrow(NotFoundException::new);
        return billShareMapper.billShareEntityToBillShareModel(billShare);
    }


    public Page<BillShareModel> findAll(Pageable pageable) {
        Page<BillShareEntity> billShares = billShareRepository.findAll(pageable);
        return billShares.map(billShareMapper::billShareEntityToBillShareModel);
    }

    public void deleteById(Long id) {
        billShareRepository.deleteById(id);
    }

    public BillShareEntity getMyBillShareInGroup(Long billId, Long groupId, Long userId) throws NotFoundException {
        return billShareRepository.findByBill_IdAndUserFriendGroup_Group_IdAndUserFriendGroup_User_Id(billId, groupId, userId)
                .orElseThrow(NotFoundException::new);
    }

    public Page<BillShareEntity> getBillSharesOfBill(Long billId, Pageable pageable) {
        return billShareRepository.findByBill_Id(billId, pageable);
    }


    public Page<BillShareEntity> getAllBillSharesInGroup(Long groupId, Pageable pageable) {
        return billShareRepository.findByUserFriendGroup_Group_Id(groupId, pageable);
    }
}
