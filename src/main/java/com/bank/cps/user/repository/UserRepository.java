package com.bank.cps.user.repository;

import com.bank.cps.user.document.UserDocument;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserDocument, String> {
    Optional<UserDocument> findByUsername(String username);
}
