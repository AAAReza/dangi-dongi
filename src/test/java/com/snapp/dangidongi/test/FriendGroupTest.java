package com.snapp.dangidongi.test;


import com.snapp.dangidongi.DangiDongiApplicationTests;
import com.snapp.dangidongi.common.Url;
import com.snapp.dangidongi.entity.Gender;
import com.snapp.dangidongi.entity.UserEntity;
import com.snapp.dangidongi.model.FriendGroupCreateModel;
import com.snapp.dangidongi.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FriendGroupTest extends DangiDongiApplicationTests {


    @Autowired
    private UserRepository userRepository;
    private static Long userId;

    @BeforeEach
    public void saveUserEntity() {
        UserEntity user = UserEntity.builder()
                .username("aaareza" + counter++)
                .firstname("reza")
                .lastname("afzali")
                .email("reza@email.com" + counter++)
                .password("123")
                .phone(9195264326L + counter++)
                .gender(Gender.MALE)
                .birthday(LocalDate.now())
                .build();
        userId = userRepository.save(user).getId();
    }


    @Test
    public void testCreateFriendGroup() throws Exception {
        FriendGroupCreateModel friendGroupCreateModel = FriendGroupCreateModel.builder()
                .name("test")
                .description("this is description For test")
                .avatar("test avatar")
                .creator(userId)
                .build();

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(Url.FRIEND_GROUP)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(friendGroupCreateModel))).andReturn();
        assertEquals(HttpStatus.CREATED.value(), mvcResult.getResponse().getStatus());

    }

}
