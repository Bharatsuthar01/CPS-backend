package com.bank.cps.user.service;

import com.bank.cps.user.document.UserDocument;
import com.bank.cps.user.dto.CreateUserRequest;
import java.util.List;

public interface UserService {
    UserDocument create(CreateUserRequest request);
    List<UserDocument> findAll();
}
