package com.snapp.dangidongi.mapper;

import com.snapp.dangidongi.entity.FriendGroupEntity;
import com.snapp.dangidongi.model.FriendGroupCreateModel;
import com.snapp.dangidongi.model.FriendGroupModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FriendGroupMapper {

    FriendGroupMapper INSTANCE = Mappers.getMapper(FriendGroupMapper.class);

    @Mapping(target = "userFriendGroups", ignore = true)
    FriendGroupModel entityToModel(FriendGroupEntity entity);

    FriendGroupEntity modelToEntity(FriendGroupModel model);

    @Mapping(source = "creator", target = "creator.id")
    FriendGroupEntity createModelToEntity(FriendGroupCreateModel model);


    List<FriendGroupModel> entitiesToModels(List<FriendGroupEntity> entities);

    List<FriendGroupEntity> modelsToEntities(List<FriendGroupModel> models);
}
