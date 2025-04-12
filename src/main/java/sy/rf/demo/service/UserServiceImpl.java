package sy.rf.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sy.rf.demo.entity.Role;
import sy.rf.demo.entity.User;
import sy.rf.demo.repository.RoleRepository;
import sy.rf.demo.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
     UserRepository userRepository;

    @Autowired
     RoleRepository roleRepository; // 🔧 Ajouté pour corriger le bug

    @Override
    public User addUser(User user) {
        UUID roleId = user.getRole().getId();
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        user.setRole(role); // on set un Role bien persistant
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

            UUID roleId = user.getRole().getId();
            Role role = roleRepository.findById(roleId)
                    .orElseThrow(() -> new RuntimeException("Role not found"));
            existingUser.setRole(role);

            return userRepository.save(existingUser);
        }).orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}
