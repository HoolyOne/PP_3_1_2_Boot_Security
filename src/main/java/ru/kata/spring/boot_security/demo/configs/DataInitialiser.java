package ru.kata.spring.boot_security.demo.configs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.Set;

@Configuration
public class DataInitialiser {

    @Bean
    public CommandLineRunner loadData(RoleRepository roleRepository,
                                      UserService userService,
                                      PasswordEncoder passwordEncoder) {
        return (args) -> {
            if (roleRepository.findByName("ROLE_ADMIN") == null) {
                roleRepository.save(new Role("ROLE_ADMIN"));
            }
            if (roleRepository.findByName("ROLE_USER") == null) {
                roleRepository.save(new Role("ROLE_USER"));
            }
            Role adminRole = roleRepository.findByName("ROLE_ADMIN");
            Role userRole = roleRepository.findByName("ROLE_USER");

            if (userService.listUsers().stream().noneMatch(u -> u.getUsername().equals("admin"))) {
                userService.createUserWithRoles(
                        "Александр", "Мухин", 29, "admin", "admin", Set.of(adminRole, userRole)
                );
            }
            if (userService.listUsers().stream().noneMatch(u -> u.getUsername().equals("user"))) {
                userService.createUserWithRoles(
                        "Иван", "Иванов", 30, "user", "user", Set.of(userRole)
                );
            }
        };
    }
}
