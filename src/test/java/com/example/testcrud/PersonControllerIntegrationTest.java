package com.example.testcrud;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = TestCrudApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
public class PersonControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;


    @Test
    public void createPersonWithAddressesTest() throws Exception {
        String reqBody = "{\n" +
                "    \"name\": \"amin\",\n" +
                "    \"username\": \"aawwaqa\",\n" +
                "    \"phoneNumber\": \"00721100\",\n" +
                "    \"addresses\": [\"tehran\",\"tehran\",\"tehran\",\"tehran\"]\n" +
                "}";
        mvc.perform(post("/person/withAddress")
                .contentType(MediaType.APPLICATION_JSON).content(reqBody))
                .andExpect(status().isOk());
    }
}


