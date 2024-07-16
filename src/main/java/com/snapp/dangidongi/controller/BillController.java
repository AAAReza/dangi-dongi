package com.snapp.dangidongi.controller;

import com.snapp.dangidongi.common.Url;
import com.snapp.dangidongi.model.BillModel;
import com.snapp.dangidongi.service.BillService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@AllArgsConstructor
//TODO: limit access to this service in authorization
public class BillController {

    private final BillService billService;


    @PostMapping(value = Url.BILLS, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createBill(@RequestBody @Validated BillModel bill) {
        var id = billService.save(bill).getId();
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(uri).build();
    }


    @SneakyThrows
    @GetMapping(value = Url.BILLS_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BillModel> getBillById(@PathVariable Long id) {
        BillModel model = billService.findById(id);
        return ResponseEntity.ok(model);
    }


    @GetMapping(value = Url.BILLS, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<BillModel>> getBills(@ParameterObject Pageable pageable) {
        Page<BillModel> models = billService.findAll(pageable);
        return ResponseEntity.ok(models);
    }


    @DeleteMapping(value = Url.BILLS_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BillModel> deleteBillById(@PathVariable Long id) {
        billService.deleteById(id);
        return ResponseEntity.ok().build();
    }


    @GetMapping(value = Url.BILLS_GROUP_GROUP_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<BillModel>> getBillsInGroup(@PathVariable("group-id") Long groupId, @ParameterObject Pageable pageable) {
        Page<BillModel> billsInGroup = billService.getBillsInGroup(groupId, pageable);
        return ResponseEntity.ok(billsInGroup);
    }

}