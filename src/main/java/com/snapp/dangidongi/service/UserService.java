package com.snapp.dangidongi.service;

import com.snapp.dangidongi.entity.UserEntity;
import com.snapp.dangidongi.exception.NotFoundException;
import com.snapp.dangidongi.mapper.UserMapper;
import com.snapp.dangidongi.model.UserModel;
import com.snapp.dangidongi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {


    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserEntity save(UserModel user) {
        UserEntity userEntity = userMapper.userModelToUserEntity(user);
        return userRepository.save(userEntity);
    }

    public UserEntity findById(Long id) throws NotFoundException {
        return userRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public UserEntity findByPhone(Long phone) throws NotFoundException {
        return userRepository.findByPhone(phone).orElseThrow(NotFoundException::new);
    }


    public Page<UserEntity> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public Page<UserEntity> getUsersInGroup(Long groupId, Pageable pageable) {
        return userRepository.findByUserFriendGroups_Group_Id(groupId, pageable);
    }
}
