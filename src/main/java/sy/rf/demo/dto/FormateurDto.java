package sy.rf.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class FormateurDto {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String tel;
    private String type;
    private EmployeurDto employeur;
    private UserDto user;
}
