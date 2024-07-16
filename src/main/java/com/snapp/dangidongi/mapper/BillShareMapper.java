package com.snapp.dangidongi.mapper;

import com.snapp.dangidongi.entity.BillShareEntity;
import com.snapp.dangidongi.model.BillShareModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BillShareMapper {

    BillShareMapper INSTANCE = Mappers.getMapper(BillShareMapper.class);

    BillShareModel billShareEntityToBillShareModel(BillShareEntity billShareEntity);

    BillShareEntity billShareModelToBillShareEntity(BillShareModel billShareModel);


    List<BillShareModel> billShareEntitiesToBillShareModels(List<BillShareEntity> billShareEntities);

    List<BillShareEntity> billShareModelsToBillShareEntities(List<BillShareModel> billShareModels);
}
