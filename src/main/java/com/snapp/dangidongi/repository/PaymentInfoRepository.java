package com.snapp.dangidongi.repository;

import com.snapp.dangidongi.entity.PaymentInfoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentInfoRepository extends JpaRepository<PaymentInfoEntity, Long> {


    Page<PaymentInfoEntity> findByBillShare_Bill_Id(Long billId, Pageable pageable);

    Page<PaymentInfoEntity> findByBillShare_UserFriendGroup_User_Id(Long userId, Pageable pageable);

    Page<PaymentInfoEntity> findByBillShare_UserFriendGroup_Group_Id(Long groupId, Pageable pageable);

    Optional<PaymentInfoEntity> findByBillShare_Id(Long billShareId);

}
