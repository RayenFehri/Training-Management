package sy.rf.demo.restController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sy.rf.demo.entity.User;
import sy.rf.demo.repository.UserRepository;
import sy.rf.demo.service.UserService;

import java.util.*;

@RestController
@RequestMapping("/users")
public class UserRestController {

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    UserRepository userRepository;

    @Autowired
     UserService userService;

    @PostMapping
    ResponseEntity<?> createUser (@RequestBody User user){
        HashMap<String, Object> response = new HashMap<>();
        if(userRepository.existsByEmail(user.getEmail())){
            response.put("message", "email exist deja !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }else{
            user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
            User savedUser = userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);}

    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.updateUser(id, user));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginClient(@RequestBody User user) {
        System.out.println("in login-client"+user);
        HashMap<String, Object> response = new HashMap<>();

        User userFromDB = userRepository.findByEmail(user.getEmail());
        System.out.println("userFromDB+client"+userFromDB);
        if (userFromDB == null) {
            response.put("message", "Client not found !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            boolean compare = this.bCryptPasswordEncoder.matches(user.getPassword(), userFromDB.getPassword());
            System.out.println("compare"+compare);
            if (!compare) {
                response.put("message", "Client not found !");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }else
            {
                String token = Jwts.builder()
                        .claim("data", userFromDB)
                        .signWith(SignatureAlgorithm.HS256, "SECRET")
                        .compact();
                response.put("token", token);
                System.out.println("hhh");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }

        }
    }

}
