package sy.rf.demo.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sy.rf.demo.dto.ParticipantDto;
import sy.rf.demo.entity.Formation;
import sy.rf.demo.models.ParticipantRequestData;
import sy.rf.demo.service.FormationService;
import sy.rf.demo.service.ParticipantService;
import sy.rf.demo.service.ProfilService;
import sy.rf.demo.service.StructureService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/participants")
@SecurityRequirement(name = "bearerAuth")
public class ParticipantRestController {
    private final ParticipantService participantService;
    private final StructureService structureService;
    private final ProfilService profilService;
    private final FormationService formationService; // Ajout du service de formation

    public ParticipantRestController(
            ParticipantService participantService,
            StructureService structureService,
            ProfilService profilService,
            FormationService formationService) { // Ajout du service dans le constructeur
        this.participantService = participantService;
        this.structureService = structureService;
        this.profilService = profilService;
        this.formationService = formationService;
    }

    @PostMapping
    public ResponseEntity<ParticipantDto> create(@RequestBody ParticipantRequestData data) {
        // Récupération des formations à partir des IDs
        Set<Formation> formations = new HashSet<>();
        if (data.getFormationIds() != null && !data.getFormationIds().isEmpty()) {
            for (UUID formationId : data.getFormationIds()) {
                formations.add(formationService.getFormationEntityById(formationId));
            }
        }

        ParticipantDto participant = ParticipantDto.builder()
                .nom(data.getNom())
                .prenom(data.getPrenom())
                .email(data.getEmail())
                .tel(data.getTel())
                .structure(structureService.getStructureById(data.getStructureId()))
                .profil(profilService.getProfilById(data.getProfilId()))
                .formations(formations)
                .build();
        return ResponseEntity.ok().body(participantService.createParticipant(participant));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ParticipantRequestData data) {
        if (id == null) {
            return ResponseEntity.badRequest().body("id must not be null");
        }
        if (data == null) {
            return ResponseEntity.badRequest().body("participant must not be null");
        }

        ParticipantDto participantDto = participantService.getParticipantById(id);
        if (participantDto == null) {
            return ResponseEntity.badRequest().body("participant not found");
        }

        // Récupération des formations à partir des IDs
        Set<Formation> formations = participantDto.getFormations();
        if (data.getFormationIds() != null) {
            formations = new HashSet<>();
            for (UUID formationId : data.getFormationIds()) {
                formations.add(formationService.getFormationEntityById(formationId));
            }
        }

        ParticipantDto newParticipant = ParticipantDto.builder()
                .id(id)
                .nom(data.getNom() != null ? data.getNom() : participantDto.getNom())
                .prenom(data.getPrenom() != null ? data.getPrenom() : participantDto.getPrenom())
                .email(data.getEmail() != null ? data.getEmail() : participantDto.getEmail())
                .tel(data.getTel() != null ? data.getTel() : participantDto.getTel())
                .structure(data.getStructureId() != null ? structureService.getStructureById(data.getStructureId()) : participantDto.getStructure())
                .profil(data.getProfilId() != null ? profilService.getProfilById(data.getProfilId()) : participantDto.getProfil())
                .formations(formations)
                .build();

        return ResponseEntity.ok().body(participantService.updateParticipant(newParticipant));
    }

    // Les autres méthodes restent inchangées
    @GetMapping("/{id}")
    public ResponseEntity<ParticipantDto> getOne(@PathVariable Long id) {
        return ResponseEntity.ok().body(participantService.getParticipantById(id));
    }

    @GetMapping
    public ResponseEntity<List<ParticipantDto>> getAll() {
        return ResponseEntity.ok().body(participantService.getAllParticipants());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        participantService.deleteParticipant(id);
        return ResponseEntity.noContent().build();
    }
}