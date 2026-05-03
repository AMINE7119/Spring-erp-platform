package com.amine.spring_erp_platform.auth.dto;

public record AuthenticationRequest(
        String email,
        String password
) {
}
