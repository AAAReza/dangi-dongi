package com.snapp.dangidongi.controller;

import com.snapp.dangidongi.common.Url;
import com.snapp.dangidongi.mapper.FriendGroupMapper;
import com.snapp.dangidongi.model.FriendGroupCreateModel;
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
    private final FriendGroupMapper friendGroupMapper;

    @PostMapping(value = Url.FRIEND_GROUP, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createFriendGroup(@RequestBody @Validated FriendGroupCreateModel friendGroupCreateModel) {

        var id = friendGroupService.save(friendGroupCreateModel).getId();
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(uri).build();
    }


    @SneakyThrows
    @PostMapping(value = Url.FRIEND_GROUP_ID_USERS, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> addUserToFriendGroup(@PathVariable("group-id") Long groupId, @PathVariable("user-id") Long userId) {
        friendGroupService.addUserToGroup(groupId, userId);
        return ResponseEntity.accepted().build();
    }

    @SneakyThrows
    @GetMapping(value = Url.FRIEND_GROUP_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FriendGroupModel> getGroupById(@PathVariable Long id) {
        FriendGroupModel model = friendGroupMapper.entityToModel(friendGroupService.findById(id));
        return ResponseEntity.ok(model);
    }

    @SneakyThrows
    @GetMapping(value = Url.FRIEND_GROUP_CREATOR_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<FriendGroupModel>> getOwnFriendGroups(@PathVariable("creator-id") Long userId, @ParameterObject Pageable pageable) {
        Page<FriendGroupModel> models = friendGroupService.findGroupByCreatorId(userId, pageable)
                .map(friendGroupMapper::entityToModel);
        return ResponseEntity.ok(models);
    }


    @DeleteMapping(value = Url.FRIEND_GROUP_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserModel> deleteFriendGroupById(@PathVariable Long id) {
        friendGroupService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    //TODO: user-id from current user after implement security
    @SneakyThrows
    @GetMapping(value = Url.FRIEND_GROUP_ME, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<FriendGroupModel>> getMyFriendGroups(@PathVariable("user-id") Long userId, @ParameterObject Pageable pageable) {
        Page<FriendGroupModel> models = friendGroupService.getMyFriendGroups(userId, pageable).map(friendGroupMapper::entityToModel);
        return ResponseEntity.ok(models);
    }
}
