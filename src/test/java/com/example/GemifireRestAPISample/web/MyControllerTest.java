package com.example.GemifireRestAPISample.web;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
public class MyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldTestListAPI() throws Exception {
        this.mockMvc.perform(get("/list"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<title>Gemfire Customers</title>")));
    }

    @Test
    public void shouldTestALL() throws Exception {
        this.mockMvc.perform(get("/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("[]")));
    }

    @Test
    public void shouldTestHelloId() throws Exception {
        ResultActions perform = this.mockMvc.perform(get("/hello/1"));
        perform.andExpect(status().isOk());
        MockHttpServletResponse response = perform.andReturn().getResponse();
        JSONObject json = new JSONObject(response.getContentAsString());
        Assertions.assertEquals("fname", json.get("firstname"));
        Assertions.assertEquals("lname", json.get("lastname"));
        Assertions.assertEquals(10, json.get("age"));
    }
}
