package service;

import model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAll();
    void addUser(User user);
    Optional<User> getUserByMail(String mail);
    Optional<User> getUserById(Long id);
    void updateUser(User user);
    void remove(User user);
}
