package com.snapp.dangidongi.service;

import com.snapp.dangidongi.entity.BillShareEntity;
import com.snapp.dangidongi.exception.NotFoundException;
import com.snapp.dangidongi.mapper.BillShareMapper;
import com.snapp.dangidongi.model.BillShareModel;
import com.snapp.dangidongi.repository.BillShareRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BillShareService {


    private final BillShareRepository billShareRepository;
    private final BillShareMapper billShareMapper;

    public BillShareEntity save(BillShareModel billShare) {
        BillShareEntity billShareEntity = billShareMapper.billShareModelToBillShareEntity(billShare);
        return billShareRepository.save(billShareEntity);
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

    public BillShareModel getMyBillShareInGroup(Long billId, Long groupId, Long userId) throws NotFoundException {
        return billShareRepository.findByBill_IdAndUserFriendGroup_Group_IdAndUserFriendGroup_User_Id(billId, groupId, userId)
                .map(billShareMapper::billShareEntityToBillShareModel)
                .orElseThrow(NotFoundException::new);
    }
}
