package sy.rf.demo.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sy.rf.demo.dto.EmployeurDto;
import sy.rf.demo.dto.FormateurDto;
import sy.rf.demo.entity.Formateur;
import sy.rf.demo.mappers.FormateurMapper;
import sy.rf.demo.models.FormateurRequestData;
import sy.rf.demo.service.EmployeurService;
import sy.rf.demo.service.FormateurService;
import sy.rf.demo.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/formateurs")
@SecurityRequirement(name = "bearerAuth")
public class FormateurRestController {


    private final FormateurService formateurService;
    private final EmployeurService employeurService;
    private final UserService userService;
public FormateurRestController(FormateurService formateurService, EmployeurService employeurService, UserService userService) {
    this.formateurService = formateurService;
    this.employeurService = employeurService;
    this.userService = userService;
}
    @PostMapping
    public ResponseEntity<FormateurDto> create(@RequestBody FormateurRequestData data) {
        FormateurDto formateur=FormateurDto.builder()
                .nom(data.getNom())
                .prenom(data.getPrenom())
                .email(data.getEmail())
                .tel(data.getTel())
                .type(data.getType())
                .employeur(employeurService.getEmployeurById(data.getEmployeurId()))
                .user(userService.getUserById(data.getUserId()))
                .build();
        return ResponseEntity.ok().body(formateurService.createFormateur(formateur));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormateurDto> getOne(@PathVariable Long id) {
        return ResponseEntity.ok().body(formateurService.getFormateurById(id));
    }

    @GetMapping
    public  ResponseEntity<List<FormateurDto>> getAll() {
        return ResponseEntity.ok().body(formateurService.getAllFormateurs());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody FormateurRequestData data) {
    if(id == null) {
        return ResponseEntity.badRequest().body("id must not be null");
    }
    if(data == null) {
        return ResponseEntity.badRequest().body("formateur must not be null");
    }

    FormateurDto formateurDto = formateurService.getFormateurById(id);
    if(formateurDto == null) {
        return ResponseEntity.badRequest().body("formateur not found");
    }
    FormateurDto newFormateur=FormateurDto.builder()
            .id(id)
            .nom(data.getNom()!=null?data.getNom():formateurDto.getNom())
            .prenom(data.getPrenom()!=null?data.getPrenom():formateurDto.getPrenom())
            .email(data.getEmail()!=null?data.getEmail():formateurDto.getEmail())
            .tel(data.getTel()!=null?data.getTel():formateurDto.getTel())
            .type(data.getType()!=null?data.getType():formateurDto.getType())
            .employeur(data.getEmployeurId()!=null?employeurService.getEmployeurById(data.getEmployeurId()):formateurDto.getEmployeur())
            .user(data.getUserId()!=null?userService.getUserById(data.getUserId()):formateurDto.getUser())
            .build();
         return ResponseEntity.ok().body(formateurService.updateFormateur(newFormateur));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        formateurService.deleteFormateur(id);
        return ResponseEntity.noContent().build();
    }
}
