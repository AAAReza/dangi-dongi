package com.snapp.dangidongi.repository;

import com.snapp.dangidongi.entity.FriendGroupEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendGroupRepository extends JpaRepository<FriendGroupEntity, Long> {

    Page<FriendGroupEntity> findByCreator_Id(Long creatorId, Pageable pageable);

}
