package com.snapp.dangidongi.controller;

import com.snapp.dangidongi.common.Url;
import com.snapp.dangidongi.exception.NotAllowedException;
import com.snapp.dangidongi.exception.NotFoundException;
import com.snapp.dangidongi.mapper.BillMapper;
import com.snapp.dangidongi.model.BillModel;
import com.snapp.dangidongi.security.UserDetail;
import com.snapp.dangidongi.service.BillService;
import io.swagger.v3.oas.annotations.Operation;
import java.net.URI;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@AllArgsConstructor
public class BillController {

  private final BillService billService;
  private final BillMapper billMapper;

  @Operation(
      description = "add new bill to group and save bill with share of any one have in group")
  @PreAuthorize("hasRole('ROLE_USER')")
  @PostMapping(
      value = Url.BILLS,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Long> createBill(@RequestBody @Validated BillModel bill)
      throws NotFoundException {
    var id = billService.save(bill).getId();
    URI uri =
        ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    return ResponseEntity.created(uri).build();
  }

  @Operation(
      description = "add new bill to group and save bill with share of any one have in group")
  @SneakyThrows
  @GetMapping(value = Url.BILLS_ID, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BillModel> getBillById(@PathVariable Long id) {
    BillModel model = billMapper.billEntityToBillModel(billService.findById(id));
    return ResponseEntity.ok(model);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @Operation(description = "get all bills that added")
  @GetMapping(value = Url.BILLS, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Page<BillModel>> getBills(@ParameterObject Pageable pageable) {
    Page<BillModel> models = billService.findAll(pageable).map(billMapper::billEntityToBillModel);
    return ResponseEntity.ok(models);
  }

  @Operation(description = "remove bill- only bill owner can remove bill and remove all share")
  @DeleteMapping(value = Url.BILLS_ID, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BillModel> deleteBillById(
      @AuthenticationPrincipal UserDetail userDetails, @PathVariable Long id)
      throws NotAllowedException {
    boolean result = billService.deleteByIdAndOwnerId(userDetails.getUser().getId(), id);
    if (!result) {
      throw new NotAllowedException();
    }
    return ResponseEntity.ok().build();
  }

  @Operation(description = "get all bills that added to group")
  @GetMapping(value = Url.BILLS_GROUP_GROUP_ID, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Page<BillModel>> getBillsInGroup(
      @PathVariable("group-id") Long groupId, @ParameterObject Pageable pageable) {
    Page<BillModel> billsInGroup =
        billService.getBillsInGroup(groupId, pageable).map(billMapper::billEntityToBillModel);
    return ResponseEntity.ok(billsInGroup);
  }
}
