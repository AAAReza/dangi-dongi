package com.snapp.dangidongi.repository;

import com.snapp.dangidongi.entity.BillShareEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillShareRepository extends JpaRepository<BillShareEntity, Long> {

    Optional<BillShareEntity> findByBill_IdAndUserFriendGroup_Group_IdAndUserFriendGroup_User_Id(Long billId, Long groupId, Long userId);

    Page<BillShareEntity> findByBill_Id(Long billId, Pageable pageable);

    Page<BillShareEntity> findByUserFriendGroup_Group_Id(Long groupId, Pageable pageable);
}
