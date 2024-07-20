package com.snapp.dangidongi.mapper;

import com.snapp.dangidongi.entity.BillEntity;
import com.snapp.dangidongi.entity.BillShareEntity;
import com.snapp.dangidongi.model.BillModel;
import com.snapp.dangidongi.model.BillShareModel;
import com.snapp.dangidongi.model.UserFriendGroupModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Mapper(
    componentModel = "spring",
    uses = {UserFriendGroupMapper.class})
public interface BillShareMapper {

  BillShareMapper INSTANCE = Mappers.getMapper(BillShareMapper.class);

  @Mapping(target = "bill", source = "bill", qualifiedByName = "getBill")
  BillShareModel billShareEntityToBillShareModel(BillShareEntity billShareEntity);

  @Named("getBill")
  default BillModel getBill(BillEntity billEntity) {
    if (Objects.isNull(billEntity)) {
      return null;
    }
    billEntity.setFriendGroup(null);
    billEntity.setBillOwner(null);
    return BillMapper.INSTANCE.billEntityToBillModel(billEntity);
  }

  default List<BillModel> billEntityListToBillModelList(List<BillEntity> billEntities) {
    if (Objects.isNull(billEntities)) {
      return null;
    }
    return new ArrayList<>(billEntities).stream().map(BillShareMapper.INSTANCE::getBill).toList();
  }

  BillShareEntity billShareModelToBillShareEntity(BillShareModel billShareModel);

  List<BillShareModel> billShareEntitiesToBillShareModels(List<BillShareEntity> billShareEntity);

  default List<BillShareModel> billShareEntitiesToBillShareModelsForBills(
      List<BillShareEntity> billShareEntity) {
    if (billShareEntity == null) {
      return null;
    }

    List<BillShareModel> list = new ArrayList<BillShareModel>(billShareEntity.size());
    for (BillShareEntity billShareEntity1 : billShareEntity) {
      if (Objects.nonNull(billShareEntity1.getBill())) {
        billShareEntity1.setBill(null);
      }
      if (Objects.nonNull(billShareEntity1.getUserFriendGroup())) {
        billShareEntity1.setUserFriendGroup(null);
      }

      list.add(billShareEntityToBillShareModel(billShareEntity1));
    }
    return list;
  }
}
