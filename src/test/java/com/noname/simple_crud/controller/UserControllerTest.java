package com.noname.simple_crud.controller;

import com.noname.simple_crud.model.User;
import com.noname.simple_crud.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void test() throws Exception {

        User user = new User();
        user.setId(1);
        user.setFirstName("test");
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));

        mockMvc.perform(get("/users/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"firstName\":\"test\",\"lastName\":null,\"email\":null,\"dateOfBirth\":null}"));

        verify(userRepository).findById(1L);
    }
}