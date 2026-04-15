package com.amine.spring_erp_platform.auth.service;

import com.amine.spring_erp_platform.auth.dto.AuthenticationResponse;
import com.amine.spring_erp_platform.auth.dto.RegisterRequest;
import com.amine.spring_erp_platform.auth.entity.Role;
import com.amine.spring_erp_platform.auth.entity.User;
import com.amine.spring_erp_platform.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.USER)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }
}