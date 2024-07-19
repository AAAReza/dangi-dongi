package com.snapp.dangidongi.controller;

import com.snapp.dangidongi.common.Url;
import com.snapp.dangidongi.exception.NotAllowedException;
import com.snapp.dangidongi.mapper.PaymentInfoMapper;
import com.snapp.dangidongi.model.PayRequestModel;
import com.snapp.dangidongi.model.PaymentInfoModel;
import com.snapp.dangidongi.service.PaymentInfoService;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
public class PaymentInfoController {

    private final PaymentInfoService paymentInfoService;
    private final PaymentInfoMapper paymentInfoMapper;


    @PostMapping(value = Url.PAYMENT_INFO, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentInfoModel> payment(@RequestBody PayRequestModel payRequestModel) throws NotAllowedException {
        Long id = paymentInfoService.save(payRequestModel);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(uri).build();
    }


    @GetMapping(value = Url.PAYMENT_INFO, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<PaymentInfoModel>> getAllPaymentInfoOfBill(
            @RequestParam(value = "billId", required = false) Long billId,
            @RequestParam(value = "groupId", required = false) Long groupId,
            @RequestParam(value = "userId", required = false) Long userId,
            @ParameterObject Pageable pageable) {
        Page<PaymentInfoModel> models = new PageImpl<>(List.of());
        if (billId == null && groupId == null && userId == null) {
            models = paymentInfoService.findAll(pageable)
                    .map(paymentInfoMapper::paymentInfoEntityToPaymentInfoModel);
        }
        if (billId != null) {
            models = paymentInfoService.getPaymentInfosOfBill(billId, pageable)
                    .map(paymentInfoMapper::paymentInfoEntityToPaymentInfoModel);
        }
        if (groupId != null) {
            models = paymentInfoService.getAllPaymentInfosInGroup(groupId, pageable)
                    .map(paymentInfoMapper::paymentInfoEntityToPaymentInfoModel);
        }
        if (userId != null) {
            models = paymentInfoService.getAllPaymentInfosOfUser(userId, pageable)
                    .map(paymentInfoMapper::paymentInfoEntityToPaymentInfoModel);
        }

        return ResponseEntity.ok(models);
    }


}
