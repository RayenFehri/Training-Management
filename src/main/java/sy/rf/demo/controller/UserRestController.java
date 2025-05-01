package sy.rf.demo.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sy.rf.demo.dto.UserDto;
import sy.rf.demo.models.UserRequestData;
import sy.rf.demo.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
@SecurityRequirement(name = "bearerAuth")
public class UserRestController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    public UserRestController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserRequestData data) {
        UserDto user = UserDto.builder()
                .nom(data.getNom())
                .prenom(data.getPrenom())
                .email(data.getEmail())
                .password(passwordEncoder.encode(data.getPassword()))
                .role(data.getRole())
                .build();
        return ResponseEntity.ok().body(userService.addUser(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getOne(@PathVariable Long id) {
        UserDto userDto = userService.getUserById(id);
        if (userDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(userDto);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UserRequestData data) {
        if (id == null) {
            return ResponseEntity.badRequest().body("id must not be null");
        }
        if (data == null) {
            return ResponseEntity.badRequest().body("user must not be null");
        }

        UserDto userDto = userService.getUserById(id);
        if (userDto == null) {
            return ResponseEntity.badRequest().body("user not found");
        }

        UserDto newUser = UserDto.builder()
                .id(id)
                .nom(data.getNom() != null ? data.getNom() : userDto.getNom())
                .prenom(data.getPrenom() != null ? data.getPrenom() : userDto.getPrenom())
                .email(data.getEmail() != null ? data.getEmail() : userDto.getEmail())
                .password(data.getPassword() != null ? passwordEncoder.encode(data.getPassword()) : userDto.getPassword())
                .role(data.getRole() != null ? data.getRole() : userDto.getRole())
                .build();

        return ResponseEntity.ok().body(userService.updateUser(newUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}