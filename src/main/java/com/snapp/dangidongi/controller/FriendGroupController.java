package com.snapp.dangidongi.controller;

import com.snapp.dangidongi.common.Url;
import com.snapp.dangidongi.model.FriendGroupModel;
import com.snapp.dangidongi.model.UserModel;
import com.snapp.dangidongi.service.FriendGroupService;
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
public class FriendGroupController {

    private final FriendGroupService friendGroupService;


    @PostMapping(value = Url.FRIEND_GROUP, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createFriendGroup(@RequestBody @Validated FriendGroupModel friendGroupModel) {
        var id = friendGroupService.save(friendGroupModel).getId();
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(uri).build();
    }


    @SneakyThrows
    @GetMapping(value = Url.FRIEND_GROUP_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FriendGroupModel> getGroupById(@PathVariable Long id) {
        FriendGroupModel model = friendGroupService.findById(id);
        return ResponseEntity.ok(model);
    }

    @SneakyThrows
    @GetMapping(value = Url.FRIEND_GROUP_USERS_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<FriendGroupModel>> getOwnFriendGroups(@PathVariable("user-id") Long userId, @ParameterObject Pageable pageable) {
        Page<FriendGroupModel> models = friendGroupService.findGroupByUserId(userId, pageable);
        return ResponseEntity.ok(models);
    }


    @DeleteMapping(value = Url.FRIEND_GROUP_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserModel> deleteUserById(@PathVariable Long id) {
        friendGroupService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
