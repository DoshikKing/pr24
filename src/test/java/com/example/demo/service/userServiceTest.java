package com.example.demo.service;

import com.example.demo.components.User;
import com.example.demo.repos.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class userServiceTest {
    @Mock
    private UserRepo userRepo;
    @Captor
    ArgumentCaptor<User> captor;
    @Test
    void getUsers() {
        User user = new User();
        user.setLogin("Vasya");
        User user2 = new User();
        user2.setLogin("Dima");
        Mockito.when(userRepo.findAll()).thenReturn(List.of(user, user2));
        assertEquals("Vasya", userRepo.findAll().get(0).getLogin());
        assertEquals("Dima", userRepo.findAll().get(1).getLogin());
    }
    @Test
    void userExists() {
        Long longInt = new Long(1);
        User user = new User();
        user.setLogin("Vasya");
        user.setId(longInt);
        user.setPassword("123456");
        userService us = new userService(userRepo);
        Mockito.when(userRepo.findByLogin("Vasya")).thenReturn(user);
        Assertions.assertEquals("Vasya", us.loadUserByUsername("Vasya").getUsername());
    }
}
