package com.bank.cps.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

public record CreateUserRequest(@NotBlank String username, @NotBlank String fullName, @Email String email,
                                @NotBlank String password, List<String> roles, List<String> permissions) {}
