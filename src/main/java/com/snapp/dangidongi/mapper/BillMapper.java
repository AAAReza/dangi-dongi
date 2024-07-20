package com.snapp.dangidongi.mapper;

import com.snapp.dangidongi.entity.*;
import com.snapp.dangidongi.model.BillModel;
import com.snapp.dangidongi.model.BillShareModel;
import com.snapp.dangidongi.model.FriendGroupModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Mapper(
    componentModel = "spring",
    uses = {UserMapper.class, BillShareMapper.class, FriendGroupMapper.class})
public interface BillMapper {

  BillMapper INSTANCE = Mappers.getMapper(BillMapper.class);

  @Mapping(target = "friendGroup", source = "friendGroup", qualifiedByName = "mapFriendGroup")
  @Mapping(target = "billShares", ignore = true)
  @Mapping(target = "billOwner.password", ignore = true)
  BillModel billEntityToBillModel(BillEntity billEntity);

  @Named("mapFriendGroup")
  default FriendGroupModel mapFriendGroup(FriendGroupEntity entity) {
    if (Objects.isNull(entity)) {
      return null;
    }
    entity.setBills(null);
    entity.setUserFriendGroups(null);
    return FriendGroupMapper.INSTANCE.entityToModel(entity);
  }

  BillEntity billModelToBillEntity(BillModel billModel);
}
