package com.apnageneralstore.service;

import com.apnageneralstore.dto.ResponseDto;
import com.apnageneralstore.dto.user.RegisterDto;
import com.apnageneralstore.exception.CustomException;
import com.apnageneralstore.repository.IUserRepository;
import com.apnageneralstore.repository.entity.AuthenticationToken;
import com.apnageneralstore.repository.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import javax.xml.crypto.dsig.DigestMethod;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    public static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    IUserRepository userRepository;

    @Autowired
    AuthenticationService authenticationService;

    public List<User> listAllUsers() {
        return userRepository.findAll();
    }

    public ResponseDto register(RegisterDto registerDto) {
        if (Objects.nonNull(userRepository.findByEmail(registerDto.getEmail())))
            throw new CustomException("User Already Exist");

        String encryptedPassword = "";
        try {
            encryptedPassword = hashPassword(registerDto.getPassword());
        } catch (NoSuchAlgorithmException exception) {
            exception.printStackTrace();
            logger.error("Hashing password failed: ", exception.getMessage());
        }
        User user = User.builder()
                .firstName(registerDto.getFirstName())
                .lastName(registerDto.getLastName())
                .email(registerDto.getEmail())
                .password(encryptedPassword)
                .build();
        userRepository.save(user);

        AuthenticationToken authenticationToken = new AuthenticationToken(user);
        authenticationService.saveConfirmationToken(authenticationToken);
        return new ResponseDto("SUCCESS", "Account created successfully.");
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        return DatatypeConverter.printHexBinary(digest).toUpperCase();
    }
}