package com.snapp.dangidongi.service;

import com.snapp.dangidongi.entity.BillShareEntity;
import com.snapp.dangidongi.entity.PaymentInfoEntity;
import com.snapp.dangidongi.exception.NotAllowedException;
import com.snapp.dangidongi.exception.NotFoundException;
import com.snapp.dangidongi.mapper.PaymentInfoMapper;
import com.snapp.dangidongi.model.PayRequestModel;
import com.snapp.dangidongi.model.PaymentInfoModel;
import com.snapp.dangidongi.repository.BillShareRepository;
import com.snapp.dangidongi.repository.PaymentInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PaymentInfoService {

  private final PaymentInfoRepository paymentInfoRepository;
  private final PaymentInfoMapper paymentInfoMapper;
  private final BillShareRepository billShareRepository;

  public Long save(PayRequestModel payRequestModel) throws NotAllowedException {
    BillShareEntity billShareEntity =
        billShareRepository.findById(payRequestModel.getBillShare()).get();
    if (paymentInfoRepository.findByBillShare_Id(billShareEntity.getId()).isPresent()) {
      throw new NotAllowedException();
    }

    PaymentInfoEntity paymentInfoEntity =
        PaymentInfoEntity.builder()
            .billShare(billShareEntity)
            .amount(billShareEntity.getShareAmount())
            .trackId(UUID.randomUUID().toString())
            .paymentDate(LocalDateTime.now())
            .build();
    return paymentInfoRepository.save(paymentInfoEntity).getId();
  }

  public PaymentInfoModel findById(Long id) throws NotFoundException {
    PaymentInfoEntity paymentInfo =
        paymentInfoRepository.findById(id).orElseThrow(NotFoundException::new);
    return paymentInfoMapper.paymentInfoEntityToPaymentInfoModel(paymentInfo);
  }

  public Page<PaymentInfoEntity> findAll(Pageable pageable) {
    return paymentInfoRepository.findAll(pageable);
  }

  public Page<PaymentInfoEntity> getPaymentInfosOfBill(Long billId, Pageable pageable) {
    return paymentInfoRepository.findByBillShare_Bill_Id(billId, pageable);
  }

  public Page<PaymentInfoEntity> getAllPaymentInfosInGroup(Long groupId, Pageable pageable) {
    return paymentInfoRepository.findByBillShare_UserFriendGroup_Group_Id(groupId, pageable);
  }

  public Page<PaymentInfoEntity> getAllPaymentInfosOfUser(Long userId, Pageable pageable) {
    return paymentInfoRepository.findByBillShare_UserFriendGroup_User_Id(userId, pageable);
  }
}
