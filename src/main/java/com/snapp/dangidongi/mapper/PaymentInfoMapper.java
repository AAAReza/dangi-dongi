package com.snapp.dangidongi.mapper;

import com.snapp.dangidongi.entity.BillShareEntity;
import com.snapp.dangidongi.entity.PaymentInfoEntity;
import com.snapp.dangidongi.model.BillShareModel;
import com.snapp.dangidongi.model.PaymentInfoModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Objects;

@Mapper(
    componentModel = "spring",
    uses = {UserMapper.class, BillShareMapper.class})
public interface PaymentInfoMapper {

  PaymentInfoMapper INSTANCE = Mappers.getMapper(PaymentInfoMapper.class);

  @Mapping(target = "billShare", source = "billShare", qualifiedByName = "getBillShare")
  PaymentInfoModel paymentInfoEntityToPaymentInfoModel(PaymentInfoEntity paymentInfoEntity);

  @Named("getBillShare")
  default BillShareModel getBillShare(BillShareEntity billShareEntity) {
    if (Objects.isNull(billShareEntity)) {
      return null;
    }
    return BillShareMapper.INSTANCE.billShareEntityToBillShareModel(billShareEntity);
  }
}
