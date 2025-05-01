package sy.rf.demo.models;

import lombok.Data;
import sy.rf.demo.entity.Role;

import java.util.UUID;

@Data
public class RegisterRequest {
    private String email;
    private String password;
    private Role role;
    private String nom;
    private String prenom;
}
