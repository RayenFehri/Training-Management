package sy.rf.demo.service;

import sy.rf.demo.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    User addUser(User user);
    Optional<User> getUserById(UUID id);
    List<User> getAllUsers();
    User updateUser(UUID id, User user);
    void deleteUser(UUID id);
}

