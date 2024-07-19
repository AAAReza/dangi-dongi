package com.snapp.dangidongi.controller;

import com.snapp.dangidongi.common.Url;
import com.snapp.dangidongi.exception.NotFoundException;
import com.snapp.dangidongi.mapper.BillShareMapper;
import com.snapp.dangidongi.model.BillShareModel;
import com.snapp.dangidongi.service.BillShareService;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
//TODO: limit access to this service in authorization
public class BillShareController {

    private final BillShareService billShareService;
    private final BillShareMapper billShareMapper;

    @GetMapping(value = Url.BILLS_SHARE_BILL_ID_GROUP_GROUP_ID_ME, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BillShareModel> getMyBillShareInGroup(@PathVariable("bill-id") Long billId,
                                                                @PathVariable("group-id") Long groupId,
                                                                @PathVariable("user-id") Long userId) throws NotFoundException {
        BillShareModel model = billShareMapper.billShareEntityToBillShareModel(billShareService.getMyBillShareInGroup(billId, groupId, userId));
        return ResponseEntity.ok(model);
    }


    @GetMapping(value = Url.BILLS_SHARE_BILLS_BILL_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<BillShareModel>> getAllBillShareOfBill(@PathVariable("bill-id") Long billId,
                                                                      @ParameterObject Pageable pageable) throws NotFoundException {
        Page<BillShareModel> models = billShareService.getBillSharesOfBill(billId, pageable)
                .map(billShareMapper::billShareEntityToBillShareModel);
        return ResponseEntity.ok(models);
    }

    @GetMapping(value = Url.BILLS_SHARE_BILL_ID_GROUP_GROUP_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<BillShareModel>> getAllBillShareOfGroup(@PathVariable("group-id") Long groupId,
                                                                       @ParameterObject Pageable pageable) throws NotFoundException {
        Page<BillShareModel> models = billShareService.getAllBillSharesInGroup(groupId, pageable)
                .map(billShareMapper::billShareEntityToBillShareModel);
        return ResponseEntity.ok(models);
    }

}
