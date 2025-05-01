package sy.rf.demo.restController;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sy.rf.demo.entity.Profil;
import sy.rf.demo.service.ProfilService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/profils")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ProfilRestController {

    @Autowired
    private ProfilService profilService;

    @PostMapping
    public Profil create(@RequestBody Profil profil) {
        return profilService.createProfil(profil);
    }

    @GetMapping("/{id}")
    public Profil getOne(@PathVariable UUID id) {
        return profilService.getProfilById(id);
    }

    @GetMapping
    public List<Profil> getAll() {
        return profilService.getAllProfils();
    }

    @PutMapping("/{id}")
    public Profil update(@PathVariable UUID id, @RequestBody Profil profil) {
        return profilService.updateProfil(id, profil);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        profilService.deleteProfil(id);
    }
}
