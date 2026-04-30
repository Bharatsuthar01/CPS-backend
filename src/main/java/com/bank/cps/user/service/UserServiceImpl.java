package com.bank.cps.user.service;

import com.bank.cps.common.exception.BusinessException;
import com.bank.cps.user.document.UserDocument;
import com.bank.cps.user.dto.CreateUserRequest;
import com.bank.cps.user.repository.UserRepository;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDocument create(CreateUserRequest request) {
        if (repository.findByUsername(request.username()).isPresent()) {
            throw new BusinessException("USER_EXISTS", "Username already exists");
        }
        UserDocument doc = new UserDocument();
        doc.setId(UUID.randomUUID().toString());
        doc.setUsername(request.username());
        doc.setFullName(request.fullName());
        doc.setEmail(request.email());
        doc.setPasswordHash(passwordEncoder.encode(request.password()));
        doc.setRoles(request.roles() == null ? List.of("MAKER") : request.roles());
        doc.setPermissions(request.permissions() == null ? List.of() : request.permissions());
        doc.setStatus("ACTIVE");
        doc.setCreatedAt(Instant.now());
        doc.setUpdatedAt(Instant.now());
        return repository.save(doc);
    }

    public List<UserDocument> findAll() { return repository.findAll(); }
}
