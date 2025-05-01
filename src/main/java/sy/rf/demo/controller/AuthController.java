package sy.rf.demo.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import sy.rf.demo.dto.UserDto;

import org.springframework.web.bind.annotation.*;
import sy.rf.demo.models.AuthRequest;
import sy.rf.demo.models.UserRequestData;
import sy.rf.demo.security.JwtUtil;
import sy.rf.demo.service.UserService;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtils;

public AuthController(AuthenticationManager authenticationManager, UserService userService, PasswordEncoder passwordEncoder,JwtUtil jwtUtils) {
    this.authenticationManager = authenticationManager;
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
    this.jwtUtils = jwtUtils;
}
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthRequest data) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        data.getEmail(),
                        data.getPassword()
                )
        );

        authentication.getPrincipal();
        UserDto userDto = userService.getUserByEmail(data.getEmail());
        String token = jwtUtils.generateToken(userDto);
        Map<String, Object> response = new HashMap<>();
        response.put("email", userDto.getEmail());
        response.put("token", token);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRequestData data) throws BadRequestException {
        boolean wrongRequest=data==null || data.getPassword()==null || data.getEmail()==null ;
        if(wrongRequest){
            throw new BadRequestException("Invalid input data, please try again");
        }
        UserDto userDto=UserDto.builder()
                .prenom(data.getPrenom())
                .nom(data.getNom())
                .email(data.getEmail())
                .password(passwordEncoder.encode(data.getPassword()))
                .role(data.getRole())
                .build();
        UserDto response=userService.addUser(userDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }



    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/logout")
    public ResponseEntity<?> logoutUser() {
userService.logout();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}

