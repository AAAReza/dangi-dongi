package com.snapp.dangidongi.test;

import com.snapp.dangidongi.DangiDongiApplicationTests;
import com.snapp.dangidongi.common.Url;
import com.snapp.dangidongi.entity.Gender;
import com.snapp.dangidongi.entity.UserEntity;
import com.snapp.dangidongi.mapper.UserMapper;
import com.snapp.dangidongi.model.UserModel;
import com.snapp.dangidongi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest extends DangiDongiApplicationTests {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;


    private UserModel buildUserModel() {
        return UserModel.builder()
                .username("aaareza" + counter++)
                .firstname("reza")
                .lastname("afzali")
                .email("reza@email.com" + counter++)
                .password("123")
                .phone(9195264326L + counter++)
                .gender(Gender.MALE)
                .birthday(LocalDate.now())
                .build();
    }

    private UserEntity createUserEntityAndSave() {
        UserEntity userEntity = userMapper.userModelToUserEntity(buildUserModel());
        return userRepository.save(userEntity);

    }

    @Test
    public void createUserSuccess() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(Url.USERS)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(buildUserModel()))).andReturn();
        assertEquals(HttpStatus.CREATED.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    public void createInvalidUser() throws Exception {
        UserModel userModel = buildUserModel();
        userModel.setUsername(null);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(Url.USERS)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(userModel))).andReturn();
        assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    public void getUserByPhone() throws Exception {
        UserEntity userEntity = createUserEntityAndSave();
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(Url.USERS_PHONE, String.valueOf(userEntity.getPhone())))
                .andReturn();
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertTrue(mvcResult.getResponse().getContentAsString().contains(String.valueOf(userEntity.getPhone())));
    }


    @Test
    public void getUserByPhoneNotFound() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(Url.USERS_PHONE, "000"))
                .andReturn();
        assertEquals(HttpStatus.NOT_FOUND.value(), mvcResult.getResponse().getStatus());
    }

}
