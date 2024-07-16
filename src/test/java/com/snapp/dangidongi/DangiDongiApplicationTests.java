package com.snapp.dangidongi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@SpringBootTest
public class DangiDongiApplicationTests {

    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    protected WebApplicationContext webApplicationContext;
    protected MockMvc mvc;
    protected static Integer counter = 0;

    @BeforeEach
    void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

}
