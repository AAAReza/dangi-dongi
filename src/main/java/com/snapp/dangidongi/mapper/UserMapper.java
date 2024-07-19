package com.snapp.dangidongi.mapper;

import com.snapp.dangidongi.entity.FriendGroupEntity;
import com.snapp.dangidongi.entity.UserEntity;
import com.snapp.dangidongi.entity.UserFriendGroupEntity;
import com.snapp.dangidongi.model.FriendGroupModel;
import com.snapp.dangidongi.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "friendGroups", source = "userFriendGroups", qualifiedByName = "getGroups")
    UserModel userEntityToUserModel(UserEntity userEntity);

    @Named("getGroups")
    default List<FriendGroupModel> getGroups(List<UserFriendGroupEntity> entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        List<FriendGroupEntity> groupEntities = entity.stream()
                .map(UserFriendGroupEntity::getGroup).toList();
        groupEntities.forEach(e -> {
            if (Objects.nonNull(e))
                e.setUserFriendGroups(null);
        });
        return groupEntities.stream().map(FriendGroupMapper.INSTANCE::entityToModel).toList();
    }

    UserEntity userModelToUserEntity(UserModel userModel);


    List<UserModel> userEntitiesToUserModels(List<UserEntity> userEntities);

    List<UserEntity> userModelsToUserEntities(List<UserModel> userModels);
}
