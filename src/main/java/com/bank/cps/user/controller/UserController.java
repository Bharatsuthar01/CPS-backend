package com.bank.cps.user.controller;

import com.bank.cps.common.api.ApiResponse;
import com.bank.cps.common.logging.CorrelationIdFilter;
import com.bank.cps.user.document.UserDocument;
import com.bank.cps.user.dto.CreateUserRequest;
import com.bank.cps.user.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService service;
    public UserController(UserService service) { this.service = service; }

    @GetMapping
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasAuthority('*')")
    public ApiResponse<List<UserDocument>> list() {
        return ApiResponse.ok("Users fetched", CorrelationIdFilter.current(), service.findAll());
    }

    @PostMapping
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasAuthority('USER_ADMIN') or hasAuthority('*')")
    public ApiResponse<UserDocument> create(@Valid @RequestBody CreateUserRequest request) {
        return ApiResponse.ok("User created", CorrelationIdFilter.current(), service.create(request));
    }
}
