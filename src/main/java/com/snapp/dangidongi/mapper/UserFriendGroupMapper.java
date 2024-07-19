package com.snapp.dangidongi.mapper;

import com.snapp.dangidongi.entity.UserFriendGroupEntity;
import com.snapp.dangidongi.model.UserFriendGroupModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserFriendGroupMapper {

    UserFriendGroupMapper INSTANCE = Mappers.getMapper(UserFriendGroupMapper.class);

    @Mapping(target = "group", ignore = true)
    UserFriendGroupModel entityToModel(UserFriendGroupEntity entity);

    UserFriendGroupEntity modelToEntity(UserFriendGroupModel model);


    List<UserFriendGroupModel> entitiesToModels(List<UserFriendGroupEntity> entities);

    List<UserFriendGroupEntity> modelsToEntities(List<UserFriendGroupModel> models);
}
