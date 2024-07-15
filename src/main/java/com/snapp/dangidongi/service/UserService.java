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

    public UserModel findById(Long id) throws NotFoundException {
        UserEntity user = userRepository.findById(id).orElseThrow(NotFoundException::new);
        return userMapper.userEntityToUserModel(user);
    }


    public Page<UserModel> findAll(Pageable pageable) {
        Page<UserEntity> users = userRepository.findAll(pageable);
        return users.map(userMapper::userEntityToUserModel);
    }
}
