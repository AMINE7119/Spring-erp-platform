package com.amine.spring_erp_platform.auth.service;

import com.amine.spring_erp_platform.auth.dto.AuthenticationRequest;
import com.amine.spring_erp_platform.auth.dto.AuthenticationResponse;
import com.amine.spring_erp_platform.auth.dto.RegisterRequest;
import com.amine.spring_erp_platform.auth.entity.Role;
import com.amine.spring_erp_platform.auth.entity.User;
import com.amine.spring_erp_platform.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.USER) // Rôle par défaut
                .build();
        repository.save(user);

        // Injection du rôle dans les claims du JWT
        var extraClaims = Map.<String, Object>of("role", user.getRole().name());
        var jwtToken = jwtService.generateToken(extraClaims, user);

        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        var user = repository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("User not found"));

        var extraClaims = Map.<String, Object>of("role", user.getRole().name());
        var jwtToken = jwtService.generateToken(extraClaims, user);

        return new AuthenticationResponse(jwtToken);
    }
}