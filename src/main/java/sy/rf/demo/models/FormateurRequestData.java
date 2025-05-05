package sy.rf.demo.models;

import lombok.Getter;
@Getter
public class FormateurRequestData {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String tel;
    private String type;
    private Long employeurId;
    private Long userId;
}
