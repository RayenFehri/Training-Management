package sy.rf.demo.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sy.rf.demo.dto.ProfileDto;
import sy.rf.demo.service.ProfilService;

import java.util.List;

@RestController
@RequestMapping("/profils")
@SecurityRequirement(name = "bearerAuth")
public class ProfilRestController {

    private final ProfilService profilService;

    public ProfilRestController(ProfilService profilService) {
        this.profilService = profilService;
    }

    @PostMapping
    public ResponseEntity<ProfileDto> create(@RequestBody ProfileDto profileDto) {
        profileDto.setId(null);
        return ResponseEntity.ok().body(profilService.createProfil(profileDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDto> getOne(@PathVariable Long id) {
        ProfileDto profileDto = profilService.getProfilById(id);
        if (profileDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(profileDto);
    }

    @GetMapping
    public ResponseEntity<List<ProfileDto>> getAll() {
        return ResponseEntity.ok().body(profilService.getAllProfils());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ProfileDto profileDto) {
        if (id == null) {
            return ResponseEntity.badRequest().body("id must not be null");
        }
        if (profileDto == null) {
            return ResponseEntity.badRequest().body("profil must not be null");
        }
        if(profilService.getProfilById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        profileDto.setId(id);
        return ResponseEntity.ok().body(profilService.updateProfil(profileDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        profilService.deleteProfil(id);
        return ResponseEntity.noContent().build();
    }
}