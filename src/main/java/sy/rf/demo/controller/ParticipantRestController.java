package sy.rf.demo.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sy.rf.demo.dto.ParticipantDto;
import sy.rf.demo.models.ParticipantRequestData;
import sy.rf.demo.service.ParticipantService;
import sy.rf.demo.service.ProfilService;
import sy.rf.demo.service.StructureService;

import java.util.List;

@RestController
@RequestMapping("/participants")
@SecurityRequirement(name = "bearerAuth")
public class ParticipantRestController {

    private final ParticipantService participantService;
    private final StructureService structureService;
    private final ProfilService profilService;

    public ParticipantRestController(ParticipantService participantService, StructureService structureService, ProfilService profilService) {
        this.participantService = participantService;
        this.structureService = structureService;
        this.profilService = profilService;
    }

    @PostMapping
    public ResponseEntity<ParticipantDto> create(@RequestBody ParticipantRequestData data) {
        ParticipantDto participant = ParticipantDto.builder()
                .nom(data.getNom())
                .prenom(data.getPrenom())
                .email(data.getEmail())
                .tel(data.getTel())
                .structure(structureService.getStructureById(data.getStructureId()))
                .profil(profilService.getProfilById(data.getProfilId()))
                .formations(data.getFormations())
                .build();
        return ResponseEntity.ok().body(participantService.createParticipant(participant));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParticipantDto> getOne(@PathVariable Long id) {
        return ResponseEntity.ok().body(participantService.getParticipantById(id));
    }

    @GetMapping
    public ResponseEntity<List<ParticipantDto>> getAll() {
        return ResponseEntity.ok().body(participantService.getAllParticipants());
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

        ParticipantDto newParticipant = ParticipantDto.builder()
                .id(id)
                .nom(data.getNom() != null ? data.getNom() : participantDto.getNom())
                .prenom(data.getPrenom() != null ? data.getPrenom() : participantDto.getPrenom())
                .email(data.getEmail() != null ? data.getEmail() : participantDto.getEmail())
                .tel(data.getTel() != null ? data.getTel() : participantDto.getTel())
                .structure(data.getStructureId() != null ? structureService.getStructureById(data.getStructureId()) : participantDto.getStructure())
                .profil(data.getProfilId() != null ? profilService.getProfilById(data.getProfilId()) : participantDto.getProfil())
                .formations(data.getFormations() != null ? data.getFormations() : participantDto.getFormations())
                .build();

        return ResponseEntity.ok().body(participantService.updateParticipant(newParticipant));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        participantService.deleteParticipant(id);
        return ResponseEntity.noContent().build();
    }
}