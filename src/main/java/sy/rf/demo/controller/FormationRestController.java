package sy.rf.demo.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sy.rf.demo.dto.FormationDto;
import sy.rf.demo.models.FormationRequestData;
import sy.rf.demo.service.DomaineService;
import sy.rf.demo.service.FormateurService;
import sy.rf.demo.service.FormationService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/formations")
@SecurityRequirement(name = "bearerAuth")
public class FormationRestController {

    private final FormationService formationService;
    private final DomaineService domaineService;
    private final FormateurService formateurService;

    public FormationRestController(FormationService formationService, DomaineService domaineService, FormateurService formateurService) {
        this.formationService = formationService;
        this.domaineService = domaineService;
        this.formateurService = formateurService;
    }

    @PostMapping
    public ResponseEntity<FormationDto> create(@RequestBody FormationRequestData data) {
        FormationDto formation = FormationDto.builder()
                .titre(data.getTitre())
                .annee(data.getAnnee())
                .duree(data.getDuree())
                .budget(data.getBudget())
                .domaine(domaineService.getDomaineById(data.getDomaineId()))
                .formateur(formateurService.getFormateurById(data.getFormateurId()))
                .build();
        return ResponseEntity.ok().body(formationService.createFormation(formation));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormationDto> getOne(@PathVariable UUID id) {
        return ResponseEntity.ok().body(formationService.getFormationById(id));
    }

    @GetMapping
    public ResponseEntity<List<FormationDto>> getAll() {
        return ResponseEntity.ok().body(formationService.getAllFormations());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody FormationRequestData data) {
        if (id == null) {
            return ResponseEntity.badRequest().body("id must not be null");
        }
        if (data == null) {
            return ResponseEntity.badRequest().body("formation must not be null");
        }

        FormationDto formationDto = formationService.getFormationById(id);
        if (formationDto == null) {
            return ResponseEntity.badRequest().body("formation not found");
        }

        FormationDto newFormation = FormationDto.builder()
                .id(id)
                .titre(data.getTitre() != null ? data.getTitre() : formationDto.getTitre())
                .annee(data.getAnnee() != null ? data.getAnnee() : formationDto.getAnnee())
                .duree(data.getDuree() != null ? data.getDuree() : formationDto.getDuree())
                .budget(data.getBudget() != null ? data.getBudget() : formationDto.getBudget())
                .domaine(data.getDomaineId() != null ? domaineService.getDomaineById(data.getDomaineId()): formationDto.getDomaine())
                .formateur(data.getFormateurId() != null ? formateurService.getFormateurById(data.getFormateurId()): formationDto.getFormateur())
                .build();

        return ResponseEntity.ok().body(formationService.updateFormation(newFormation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        formationService.deleteFormation(id);
        return ResponseEntity.noContent().build();
    }
}