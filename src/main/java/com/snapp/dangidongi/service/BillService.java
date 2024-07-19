package com.snapp.dangidongi.service;

import com.snapp.dangidongi.entity.BillEntity;
import com.snapp.dangidongi.exception.NotFoundException;
import com.snapp.dangidongi.mapper.BillMapper;
import com.snapp.dangidongi.model.BillModel;
import com.snapp.dangidongi.repository.BillRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BillService {


    private final BillRepository billRepository;
    private final BillMapper billMapper;
    private final BillShareService billShareService;

    @Transactional
    public BillEntity save(BillModel bill) throws NotFoundException {
        BillEntity billEntity = billMapper.billModelToBillEntity(bill);
        billEntity = billRepository.save(billEntity);
        billShareService.calculateBillShareInGroup(billEntity, bill.getFriendGroup().getId());
        return billEntity;
    }

    public BillEntity findById(Long id) throws NotFoundException {
        return billRepository.findById(id).orElseThrow(NotFoundException::new);
    }


    public Page<BillEntity> findAll(Pageable pageable) {
        return billRepository.findAll(pageable);
    }

    public void deleteById(Long id) {
        billRepository.deleteById(id);
    }

    public Page<BillEntity> getBillsInGroup(Long groupId, Pageable pageable) {
        return billRepository.findByFriendGroup_Id(groupId, pageable);
    }
}
