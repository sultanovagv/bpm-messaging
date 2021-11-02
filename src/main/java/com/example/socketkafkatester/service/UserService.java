package com.example.socketkafkatester.service;

import com.example.socketkafkatester.config.security.CustomAuthenticationProvider;
import com.example.socketkafkatester.config.security.TokenCreator;
import com.example.socketkafkatester.entity.UserEntity;
import com.example.socketkafkatester.error.UserCommonException;
import com.example.socketkafkatester.model.UserRequest;
import com.example.socketkafkatester.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CustomAuthenticationProvider customAuthenticationProvider;
    private final TokenCreator tokenCreator;

    public String login(UserRequest userRequest) {
        var email = userRequest.getEmail();
        var password = userRequest.getPassword();

        var authentication = new UsernamePasswordAuthenticationToken(email, password);
        customAuthenticationProvider.authenticate(authentication);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return tokenCreator.createToken(authentication);
    }

    @Transactional
    public void register(UserRequest userRequest) {
        var email = userRequest.getEmail();
        var password = userRequest.getPassword();
        var user = userRepository.findByEmail(email);
        if (user == null) {
            var userEntity = new UserEntity();
            userEntity.setStatus(true);
            userRequest.setEmail(email);
            userRequest.setPassword(password);
        }
    }

}