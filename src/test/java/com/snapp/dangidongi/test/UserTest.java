package com.snapp.dangidongi.test;

import com.snapp.dangidongi.entity.Gender;
import com.snapp.dangidongi.entity.UserEntity;
import com.snapp.dangidongi.exception.NotFoundException;
import com.snapp.dangidongi.model.UserModel;
import com.snapp.dangidongi.repository.UserRepository;
import com.snapp.dangidongi.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;


    private UserEntity createAndSaveUser() {
        var userModel = UserModel.builder().firstname("reza")
                .gender(Gender.MALE)
                .build();
        return userService.save(userModel);
    }

    @Test
    public void createAndSaveUserTest() {
        var userEntity = createAndSaveUser();
        Assertions.assertNotNull(userEntity.getId());
        Assertions.assertNotNull(userEntity.getCreationTime());
        Assertions.assertEquals(userEntity.getGender(), Gender.MALE);
    }



    @Test
    public void loadUserTest() throws NotFoundException {
        createAndSaveUser();
        var loadedUser = userRepository.findAll().getFirst();
        var userModel = userService.findById(loadedUser.getId());
        Assertions.assertEquals(userModel.getId(), loadedUser.getId());
    }
}
