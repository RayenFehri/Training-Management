package sy.rf.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sy.rf.demo.entity.User;
import sy.rf.demo.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(UUID id, User user) {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            existingUser.setRole(user.getRole());
            return userRepository.save(existingUser);
        }).orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}

