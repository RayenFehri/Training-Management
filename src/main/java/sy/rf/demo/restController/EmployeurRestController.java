package sy.rf.demo.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sy.rf.demo.entity.Employeur;
import sy.rf.demo.service.EmployeurService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("/employeurs")
public class EmployeurRestController {

    @Autowired
    EmployeurService employeurService;

    @GetMapping
    public List<Employeur> getAllEmployeurs() {
        return employeurService.getAllEmployeurs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employeur> getEmployeurById(@PathVariable UUID id) {
        Optional<Employeur> employeur = employeurService.getEmployeurById(id);
        return employeur.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Employeur> createEmployeur(@RequestBody Employeur employeur) {
        Employeur savedEmployeur = employeurService.createEmployeur(employeur);
        return ResponseEntity.ok(savedEmployeur);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employeur> updateEmployeur(@PathVariable UUID id, @RequestBody Employeur employeurDetails) {
        try {
            Employeur updatedEmployeur = employeurService.updateEmployeur(id, employeurDetails);
            return ResponseEntity.ok(updatedEmployeur);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployeur(@PathVariable UUID id) {
        employeurService.deleteEmployeur(id);
        return ResponseEntity.noContent().build();
    }
}
