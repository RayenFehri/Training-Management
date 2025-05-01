package sy.rf.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
     RoleRepository roleRepository;

    @Autowired
     PasswordEncoder passwordEncoder;

    @Override
    public User addUser(User user) {
        UUID roleId = user.getRole().getId();
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        user.setRole(role);

        // 🔐 Encode le mot de passe avant sauvegarde
        user.setPassword(passwordEncoder.encode(user.getPassword()));

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
            // Update fields
            existingUser.setNom(user.getNom());
            existingUser.setPrenom(user.getPrenom());
            existingUser.setEmail(user.getEmail());

            // Update password only if provided
            if (user.getPassword() != null && !user.getPassword().isBlank()) {
                existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
            }

            // Update role
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
