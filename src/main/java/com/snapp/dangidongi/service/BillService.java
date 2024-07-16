package com.snapp.dangidongi.service;

import com.snapp.dangidongi.entity.BillEntity;
import com.snapp.dangidongi.exception.NotFoundException;
import com.snapp.dangidongi.mapper.BillMapper;
import com.snapp.dangidongi.model.BillModel;
import com.snapp.dangidongi.repository.BillRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BillService {


    private final BillRepository billRepository;
    private final BillMapper billMapper;

    public BillEntity save(BillModel bill) {
        BillEntity billEntity = billMapper.billModelToBillEntity(bill);
        return billRepository.save(billEntity);
    }

    public BillModel findById(Long id) throws NotFoundException {
        BillEntity bill = billRepository.findById(id).orElseThrow(NotFoundException::new);
        return billMapper.billEntityToBillModel(bill);
    }


    public Page<BillModel> findAll(Pageable pageable) {
        Page<BillEntity> bills = billRepository.findAll(pageable);
        return bills.map(billMapper::billEntityToBillModel);
    }

    public void deleteById(Long id) {
        billRepository.deleteById(id);
    }

    public Page<BillModel> getBillsInGroup(Long groupId, Pageable pageable) {
        return billRepository.findByFriendGroup_Id(groupId, pageable).map(billMapper::billEntityToBillModel);
    }
}
