package com.example.socketkafkatester.config.security;

import com.example.socketkafkatester.error.UserCommonException;
import com.example.socketkafkatester.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        var user = userRepository.findByEmail(email);

        if (user == null || user.getStatus().equals(false)) {
            throw new UserCommonException("User not found");
        }
        if (!user.getPassword().equals(password)) {
            throw new BadCredentialsException("Authentication failed");
        }

        return new UsernamePasswordAuthenticationToken(email, user.getPassword());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
