package com.apnageneralstore.controller;

import com.apnageneralstore.dto.ResponseDto;
import com.apnageneralstore.dto.user.RegisterDto;
import com.apnageneralstore.dto.user.SignInDto;
import com.apnageneralstore.dto.user.SignInResponseDto;
import com.apnageneralstore.exception.CustomException;
import com.apnageneralstore.repository.entity.User;
import com.apnageneralstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;
    @GetMapping("/all")
    public List<User> getAllUser(){
        return userService.listAllUsers();
    }

    @PostMapping("/signup")
    public ResponseDto register(@RequestBody RegisterDto registerDto)  throws CustomException {
        return userService.register(registerDto);
    }
    @PostMapping("/signin")
    public SignInResponseDto signIn(@RequestBody SignInDto signInDto) {
        return  userService.signIn(signInDto);
    }
}
