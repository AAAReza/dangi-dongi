package com.snapp.dangidongi.repository;

import com.snapp.dangidongi.entity.BillEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<BillEntity, Long> {

  Page<BillEntity> findByFriendGroup_Id(Long id, Pageable pageable);

  boolean deleteByIdAndBillOwner_Id(Long id, Long ownerId);
}
