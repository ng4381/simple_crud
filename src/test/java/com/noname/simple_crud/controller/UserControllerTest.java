package com.noname.simple_crud.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.noname.simple_crud.model.User;
import com.noname.simple_crud.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void getUsersByIdTest() throws Exception {

        User user = new User();
        user.setId(1);
        user.setFirstName("test");
        when(userService.getUserById(1L)).thenReturn(user);

        mockMvc.perform(get("/users/1"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(userService).getUserById(1L);
    }

    @Test
    public void createUserTest() throws Exception {

        User user = new User();
        user.setId(1);
        user.setFirstName("test");
        when(userService.create(any())).thenReturn(user);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(user);

        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
                .andExpect(status().isCreated());

        verify(userService).create(any());
    }
}