package com.example.usermanagement;

import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.usermanagement.entity.Role;
import com.example.usermanagement.entity.User;
import com.example.usermanagement.repository.RoleRepository;
import com.example.usermanagement.repository.UserRepository;

@Component
public class UserDataSeeder implements CommandLineRunner {

    private final RoleRepository roleRepo;
    private final UserRepository userRepo;
    private final PasswordEncoder encoder;

    public UserDataSeeder(RoleRepository roleRepo, UserRepository userRepo, PasswordEncoder encoder) {
        this.roleRepo = roleRepo;
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) {
        Role admin = roleRepo.findByName("ADMIN").orElseGet(() -> roleRepo.save(new Role("ADMIN")));
        Role manager = roleRepo.findByName("MANAGER").orElseGet(() -> roleRepo.save(new Role("MANAGER")));
        Role userRole = roleRepo.findByName("USER").orElseGet(() -> roleRepo.save(new Role("USER")));

        if (!userRepo.existsByEmail("admin@example.com")) {
            User u = new User("Admin", "admin@example.com", encoder.encode("admin123"));
            u.setRoles(Set.of(admin));
            userRepo.save(u);
        }
        if (!userRepo.existsByEmail("manager@example.com")) {
            User u = new User("Manager", "manager@example.com", encoder.encode("manager123"));
            u.setRoles(Set.of(manager));
            userRepo.save(u);
        }
        if (!userRepo.existsByEmail("user@example.com")) {
            User u = new User("User", "user@example.com", encoder.encode("user12345"));
            u.setRoles(Set.of(userRole));
            userRepo.save(u);
        }
    }
}
