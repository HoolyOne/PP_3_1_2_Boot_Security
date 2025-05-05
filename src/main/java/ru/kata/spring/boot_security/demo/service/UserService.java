package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    void addUser(User user);

    void createUser(String name, String surname, Integer age, String username, String password);

    void createUserWithRoles(String name, String surname, Integer age, String username, String password, Set<Role> roles);

    void updateUser(User user);

    void updateUserFields(Long id, String name, String surname, Integer age);

    List<User> listUsers();

    User getUserById(Long id);

    void removeUser(Long id);
}
