package com.amine.spring_erp_platform.auth.dto;

public record RegisterRequest(
        String firstName,
        String lastName,
        String email,
        String password
) {}