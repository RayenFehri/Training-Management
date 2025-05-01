package sy.rf.demo.models;

import lombok.Getter;
import sy.rf.demo.entity.Role;
@Getter
public class UserRequestData {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private Role role;
}
