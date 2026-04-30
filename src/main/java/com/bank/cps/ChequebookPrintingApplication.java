package com.bank.cps;

import com.bank.cps.user.document.UserDocument;
import com.bank.cps.user.repository.UserRepository;
import java.time.Instant;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableMongoAuditing
public class ChequebookPrintingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChequebookPrintingApplication.class, args);
    }

    @Bean
    CommandLineRunner seedAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                UserDocument admin = new UserDocument();
                admin.setId("USR_ADMIN");
                admin.setUsername("admin");
                admin.setFullName("System Administrator");
                admin.setEmail("admin@cps.local");
                admin.setPasswordHash(passwordEncoder.encode("Admin@123456"));
                admin.setRoles(List.of("SUPER_ADMIN"));
                admin.setPermissions(List.of("*"));
                admin.setStatus("ACTIVE");
                admin.setCreatedAt(Instant.now());
                admin.setUpdatedAt(Instant.now());
                userRepository.save(admin);
            }
        };
    }
}
