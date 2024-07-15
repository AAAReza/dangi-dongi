package com.snapp.dangidongi.mapper;

import com.snapp.dangidongi.entity.UserEntity;
import com.snapp.dangidongi.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

     UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserModel userEntityToUserModel(UserEntity userEntity);

    UserEntity userModelToUserEntity(UserModel userModel);


    List<UserModel> userEntitiesToUserModels(List<UserEntity> userEntities);

    List<UserEntity> userModelsToUserEntities(List<UserModel> userModels);
}
