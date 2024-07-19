package com.snapp.dangidongi.test;


import com.snapp.dangidongi.DangiDongiApplicationTests;
import com.snapp.dangidongi.common.Url;
import com.snapp.dangidongi.entity.Gender;
import com.snapp.dangidongi.entity.UserEntity;
import com.snapp.dangidongi.model.FriendGroupCreateModel;
import com.snapp.dangidongi.repository.UserRepository;
import com.snapp.dangidongi.security.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class FriendGroupTest extends DangiDongiApplicationTests {


    @Autowired
    private UserRepository userRepository;
    private static Long userId;
    private static String groupLocation;


    @BeforeEach
    public void saveUserEntity() {
        UserEntity user = UserEntity.builder()
                .username("dangi0" + counter++)
                .firstname("reza")
                .lastname("afzali")
                .email("reza@email.com" + counter++)
                .password("123")
                .phone(9195264326L + counter++)
                .gender(Gender.MALE)
                .role(Role.ROLE_USER)
                .birthday(LocalDate.now())
                .build();
        userId = userRepository.save(user).getId();
    }

    @WithMockUser(username = "dangi0")
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
        groupLocation = mvcResult.getResponse().getHeaders("Location").getFirst();

    }

    @WithMockUser(username = "dangi0")
    @Test
    public void testAddUserToGroup() throws Exception {
        testGetFriendGroup();
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(Url.FRIEND_GROUP_ID_USERS, Arrays.stream(groupLocation.split("/")).toList().getLast(), userId)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        assertEquals(HttpStatus.ACCEPTED.value(), mvcResult.getResponse().getStatus());

    }

    @WithMockUser(username = "dangi0")
    @Test
    public void testGetFriendGroup() throws Exception {
        testCreateFriendGroup();
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(groupLocation)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());

    }

    @WithMockUser(username = "dangi0")
    @Test
    public void testOwnFriendGroup() throws Exception {
        testCreateFriendGroup();
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(Url.FRIEND_GROUP_CREATOR_ID, userId)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertFalse(objectMapper.readValue(mvcResult.getResponse().getContentAsByteArray(), Map.class).isEmpty());
    }

}
