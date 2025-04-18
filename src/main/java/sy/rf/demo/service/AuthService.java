package sy.rf.demo.service;

import org.springframework.security.core.Authentication;
import sy.rf.demo.dto.AuthRequest;
import sy.rf.demo.dto.AuthResponse;
import sy.rf.demo.dto.RegisterRequest;
import sy.rf.demo.entity.Role;
import sy.rf.demo.entity.User;
import sy.rf.demo.repository.RoleRepository;
import sy.rf.demo.repository.UserRepository;
import sy.rf.demo.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
     AuthenticationManager authManager;

    @Autowired
     CustomUserDetailsService userDetailsService;

    @Autowired
     JwtUtil jwtUtil;

    @Autowired
     UserRepository userRepository;

    @Autowired
     RoleRepository roleRepository;

    @Autowired
     PasswordEncoder passwordEncoder;

    public AuthResponse authenticate(AuthRequest request) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                request.getEmail(), request.getPassword());

        authManager.authenticate(authentication);

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = jwtUtil.generateToken(userDetails);

        return new AuthResponse(token);
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        User newUser = new User();
        newUser.setEmail(request.getEmail());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setRole(role);

        userRepository.save(newUser);

        // Générer un token immédiatement
        UserDetails userDetails = userDetailsService.loadUserByUsername(newUser.getEmail());
        String token = jwtUtil.generateToken(userDetails);

        return new AuthResponse(token);
    }
}
