package sy.rf.demo.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class RegisterRequest {
    private String email;
    private String password;
    private UUID roleId; // on attend un ID de rôle (ex: "ADMIN", "FORMATEUR", etc.)

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UUID getRoleId() {
        return roleId;
    }

    public void setRoleId(UUID roleId) {
        this.roleId = roleId;
    }
}
