package sy.rf.demo.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class RegisterRequest {
    private String email;
    private String password;
    private UUID roleId; // on attend un ID de rôle (ex: "ADMIN", "FORMATEUR", etc.)
}
