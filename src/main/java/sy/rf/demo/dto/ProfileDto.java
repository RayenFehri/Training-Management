package sy.rf.demo.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProfileDto {
    private Long id;
    private String libelle;
}
