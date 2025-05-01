package sy.rf.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sy.rf.demo.entity.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String token;
    private String nom;
    private String prenom;
    private String email;
    private Role role;
}