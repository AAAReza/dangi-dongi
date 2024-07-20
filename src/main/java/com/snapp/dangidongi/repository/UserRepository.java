package com.snapp.dangidongi.repository;

import com.snapp.dangidongi.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

  Optional<UserEntity> findByPhone(Long phone);

  @Query(
      "select u from UserEntity u inner join UserFriendGroupEntity uf on  uf.user.id = u.id where uf.group.id = :groupId")
  Page<UserEntity> findByUserFriendGroups_Group_Id(
      @Param("groupId") Long groupId, Pageable pageable);

  Optional<UserEntity> findByUsername(String username);
}
