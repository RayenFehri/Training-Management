package sy.rf.demo.restController;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sy.rf.demo.entity.Formateur;
import sy.rf.demo.service.FormateurService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/formateurs")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class FormateurRestController {

    @Autowired
    FormateurService formateurService;

    @PostMapping
    public Formateur create(@RequestBody Formateur formateur) {
        return formateurService.createFormateur(formateur);
    }

    @GetMapping("/{id}")
    public Formateur getOne(@PathVariable UUID id) {
        return formateurService.getFormateurById(id);
    }

    @GetMapping
    public List<Formateur> getAll() {
        return formateurService.getAllFormateurs();
    }

    @PutMapping("/{id}")
    public Formateur update(@PathVariable UUID id, @RequestBody Formateur formateur) {
        return formateurService.updateFormateur(id, formateur);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        formateurService.deleteFormateur(id);
    }
}
