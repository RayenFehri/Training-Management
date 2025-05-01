package sy.rf.demo.restController;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sy.rf.demo.entity.Employeur;
import sy.rf.demo.service.EmployeurService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employeurs")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeurRestController {

    @Autowired
    EmployeurService employeurService;

    @PostMapping
    public Employeur create(@RequestBody Employeur employeur) {
        return employeurService.createEmployeur(employeur);
    }

    @GetMapping("/{id}")
    public Employeur getOne(@PathVariable UUID id) {
        return employeurService.getEmployeurById(id);
    }

    @GetMapping
    public List<Employeur> getAll() {
        return employeurService.getAllEmployeurs();
    }

    @PutMapping("/{id}")
    public Employeur update(@PathVariable UUID id, @RequestBody Employeur employeur) {
        return employeurService.updateEmployeur(id, employeur);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        employeurService.deleteEmployeur(id);
    }
}
