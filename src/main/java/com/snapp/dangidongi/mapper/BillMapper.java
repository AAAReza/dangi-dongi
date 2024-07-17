package com.snapp.dangidongi.mapper;

import com.snapp.dangidongi.entity.BillEntity;
import com.snapp.dangidongi.model.BillModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BillMapper {

    BillMapper INSTANCE = Mappers.getMapper(BillMapper.class);

    @Mapping(target = "friendGroup" , ignore = true)
    BillModel billEntityToBillModel(BillEntity billEntity);

    BillEntity billModelToBillEntity(BillModel billModel);


    List<BillModel> billEntitiesToBillModels(List<BillEntity> billEntities);

    List<BillEntity> billModelsToBillEntities(List<BillModel> billModels);
}
