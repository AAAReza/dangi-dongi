package com.snapp.dangidongi.repository;

import com.snapp.dangidongi.entity.BillShareEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillShareRepository extends JpaRepository<BillShareEntity, Long> {

    Optional<BillShareEntity> findByBill_IdAndUserFriendGroup_Group_IdAndUserFriendGroup_User_Id(Long billId, Long groupId, Long userId);
}
