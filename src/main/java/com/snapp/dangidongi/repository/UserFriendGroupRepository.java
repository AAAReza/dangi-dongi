package com.snapp.dangidongi.repository;

import com.snapp.dangidongi.entity.UserFriendGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFriendGroupRepository extends JpaRepository<UserFriendGroupEntity, Long> {}
