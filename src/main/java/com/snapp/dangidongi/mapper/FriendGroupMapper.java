package com.snapp.dangidongi.mapper;

import com.snapp.dangidongi.entity.FriendGroupEntity;
import com.snapp.dangidongi.entity.UserEntity;
import com.snapp.dangidongi.entity.UserFriendGroupEntity;
import com.snapp.dangidongi.model.FriendGroupCreateModel;
import com.snapp.dangidongi.model.FriendGroupModel;
import com.snapp.dangidongi.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface FriendGroupMapper {

  FriendGroupMapper INSTANCE = Mappers.getMapper(FriendGroupMapper.class);

  @Mapping(target = "groupUsers", source = "userFriendGroups", qualifiedByName = "getUsersInGroup")
  @Mapping(target = "bills", ignore = true)
  @Mapping(target = "creator.password", ignore = true)
  FriendGroupModel entityToModel(FriendGroupEntity entity);

  @Named("getUsersInGroup")
  default List<UserModel> getUsersInGroup(List<UserFriendGroupEntity> entity) {
    if (Objects.isNull(entity)) {
      return null;
    }
    List<UserEntity> userEntities = entity.stream().map(UserFriendGroupEntity::getUser).toList();
    userEntities.forEach(
        e -> {
          if (Objects.nonNull(e)) e.setUserFriendGroups(null);
        });
    return userEntities.stream().map(UserMapper.INSTANCE::userEntityToUserModel).toList();
  }

  FriendGroupEntity modelToEntity(FriendGroupModel model);

  @Mapping(source = "creator", target = "creator.id")
  FriendGroupEntity createModelToEntity(FriendGroupCreateModel model);

  List<FriendGroupModel> entitiesToModels(List<FriendGroupEntity> entities);

  List<FriendGroupEntity> modelsToEntities(List<FriendGroupModel> models);
}
