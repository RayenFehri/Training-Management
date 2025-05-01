package sy.rf.demo.dto;

import lombok.*;
import sy.rf.demo.entity.Role;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private Role role;

}
