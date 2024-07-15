package com.snapp.dangidongi.mapper;

import com.snapp.dangidongi.entity.UserEntity;
import com.snapp.dangidongi.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

     UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserModel userEntityToUserModel(UserEntity userEntity);

    UserEntity userModelToUserEntity(UserModel userModel);
}
