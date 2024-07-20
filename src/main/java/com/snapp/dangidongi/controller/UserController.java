package com.snapp.dangidongi.controller;

import com.snapp.dangidongi.common.Url;
import com.snapp.dangidongi.mapper.UserMapper;
import com.snapp.dangidongi.model.UserModel;
import com.snapp.dangidongi.security.UserDetail;
import com.snapp.dangidongi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@AllArgsConstructor
public class UserController {

  private final UserService userService;
  private final UserMapper userMapper;

  @Operation(
      description =
          "create user by admin - need authentication and role of authorize user must be ROLE_ADMIN")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping(
      value = Url.USERS,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Long> createUser(@RequestBody @Validated UserModel user) {
    var id = userService.save(user).getId();
    URI uri =
        ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    return ResponseEntity.created(uri).build();
  }

  @Operation(
      description = "register user for the first time - this service does not need authentication")
  @PostMapping(
      value = Url.REGISTER,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Long> registerUser(@RequestBody @Validated UserModel user) {
    var id = userService.save(user).getId();
    URI uri =
        ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    return ResponseEntity.created(uri).build();
  }

  @Operation(
      description = "used to get created user by id but current user that logged in only can load")
  @SneakyThrows
  @GetMapping(value = Url.USERS_ID, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserModel> getUserById(
      @AuthenticationPrincipal UserDetail userDetails, @PathVariable Long id) {
    if (!userDetails.getUser().getId().equals(id)) {
      throw new AccessDeniedException("can not get user by id " + id);
    }
    UserModel model = userMapper.userEntityToUserModel(userService.findById(id));
    return ResponseEntity.ok(model);
  }

  @Operation(description = "get user by phone number for add user to group")
  @SneakyThrows
  @GetMapping(value = Url.USERS_PHONE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserModel> getUserByPhone(@PathVariable Long phone) {
    UserModel model = userMapper.userEntityToUserModel(userService.findByPhone(phone));
    return ResponseEntity.ok(model);
  }

  @Operation(description = "get all user- only admin can call this service")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @GetMapping(value = Url.USERS, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Page<UserModel>> getUsers(@ParameterObject Pageable pageable) {
    Page<UserModel> models = userService.findAll(pageable).map(userMapper::userEntityToUserModel);
    return ResponseEntity.ok(models);
  }

  @Operation(description = "remove user - current user can delete their own data")
  @DeleteMapping(value = Url.USERS_ID, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserModel> deleteUserById(
      @AuthenticationPrincipal UserDetail userDetails, @PathVariable Long id) {
    if (!userDetails.getUser().getId().equals(id)) {
      throw new AccessDeniedException("can not find user by id " + id);
    }
    userService.deleteById(id);
    return ResponseEntity.ok().build();
  }

  @Operation(description = "get all user in group by group id")
  @GetMapping(value = Url.USERS_GROUP_GROUP_ID, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Page<UserModel>> getUsersInGroup(
      @PathVariable("group-id") Long groupId, @ParameterObject Pageable pageable) {
    Page<UserModel> usersInGroup =
        userService.getUsersInGroup(groupId, pageable).map(userMapper::userEntityToUserModel);
    return ResponseEntity.ok(usersInGroup);
  }
}
