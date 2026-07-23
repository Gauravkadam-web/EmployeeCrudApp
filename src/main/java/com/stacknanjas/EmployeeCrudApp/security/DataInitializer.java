package com.stacknanjas.EmployeeCrudApp.security;

import com.stacknanjas.EmployeeCrudApp.enitity.User;
import com.stacknanjas.EmployeeCrudApp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // Only seed if the app_user table is completely empty, so this never
        // overwrites credentials someone has since changed.
        if (userRepository.count() == 0) {
            User admin = new User(
                    "admin",
                    passwordEncoder.encode("admin123"), // change this after first login
                    "ROLE_ADMIN"
            );
            userRepository.save(admin);
            System.out.println("=================================================");
            System.out.println(" Default admin user created:");
            System.out.println("   username: admin");
            System.out.println("   password: admin123");
            System.out.println(" Please log in and change this password.");
            System.out.println("=================================================");
        }
    }
}
