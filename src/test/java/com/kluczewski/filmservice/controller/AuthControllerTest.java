package com.kluczewski.filmservice.controller;

import com.kluczewski.filmservice.model.UserRole;
import com.kluczewski.filmservice.model.entity.User;
import com.kluczewski.filmservice.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@RunWith(SpringRunner.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Transactional
    @Test
    public void shouldLogInAndGetSecuredContent() throws Exception {
        //given
        User user = new User();
        user.setEmail("test@test.pl");
        user.setPassword(bCryptPasswordEncoder.encode("test"));
        user.setEnabled(true);
        user.setUserRole(UserRole.USER);
        userRepository.saveAndFlush(user);
        //when
        MvcResult login = mockMvc.perform(post("/login")
                .content("{\"email\": \"test@test.pl\", \"password\": \"test\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String token = login.getResponse().getHeader("Authorization");
        //then
        mockMvc.perform(get("/secured")
                .header("Authorization", token))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("secured"));

        mockMvc.perform(get("/secured"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Transactional
    @Test
    public void shouldNotLoginWhenUserNotExist() throws Exception {
        //given
        User user = new User();
        user.setEmail("test@test.pl");
        user.setPassword(bCryptPasswordEncoder.encode("test"));
        user.setEnabled(true);
        user.setUserRole(UserRole.USER);
        userRepository.saveAndFlush(user);
        //when
        MvcResult result = mockMvc.perform(post("/login")
                .content("{\"email\": \"" + user.getUsername() + "\", \"password\": \"" + user.getPassword() + "\"}"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andReturn();
        //then
        assertThat(result.getResponse().getStatus()).isEqualTo(401);
    }

    @Transactional
    @Test
    public void shouldNotLoginWhenWrongPassword() throws Exception {
        //given
        User user = new User();
        user.setEmail("test@test.pl");
        user.setPassword(bCryptPasswordEncoder.encode("test"));
        user.setEnabled(true);
        user.setUserRole(UserRole.USER);
        userRepository.saveAndFlush(user);
        //when
        MvcResult login = mockMvc.perform(post("/login")
                .content("{\"email\": \"test@test.pl\", \"password\": \"notTest\"}"))
                .andDo(print())
                .andExpect(status().isUnauthorized())
                .andReturn();
        //then
        assertThat(login.getResponse().getStatus()).isEqualTo(401);
    }
}
