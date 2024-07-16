package com.snapp.dangidongi.controller;

import com.snapp.dangidongi.common.Url;
import com.snapp.dangidongi.exception.NotFoundException;
import com.snapp.dangidongi.model.BillShareModel;
import com.snapp.dangidongi.service.BillShareService;
import lombok.AllArgsConstructor;
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


    @GetMapping(value = Url.BILLS_SHARE_BILL_ID_GROUP_GROUP_ID_ME, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BillShareModel> getMyBillShareInGroup(@PathVariable("bill-id") Long billId,
                                                                @PathVariable("group-id") Long groupId,
                                                                @PathVariable("user-id") Long userId) throws NotFoundException {
        BillShareModel model = billShareService.getMyBillShareInGroup(billId, groupId, userId);
        return ResponseEntity.ok(model);
    }


}
