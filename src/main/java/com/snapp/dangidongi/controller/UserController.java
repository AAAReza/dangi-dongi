package com.snapp.dangidongi.controller;

import com.snapp.dangidongi.common.Url;
import com.snapp.dangidongi.model.UserModel;
import com.snapp.dangidongi.service.UserService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping(value = Url.USERS, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createUser(@RequestBody @Validated UserModel user) {
        var id = userService.save(user).getId();
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(uri).build();
    }


    @SneakyThrows
    @GetMapping(value = Url.USERS_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserModel> getUsers(@PathVariable Long id) {
        UserModel model = userService.findById(id);
        return ResponseEntity.ok(model);
    }


}
