package sy.rf.demo.restController;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sy.rf.demo.entity.Formation;
import sy.rf.demo.service.FormationService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/formations")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class FormationRestController {

    @Autowired
    private FormationService formationService;

    @PostMapping
    public Formation create(@RequestBody Formation formation) {
        return formationService.createFormation(formation);
    }

    @GetMapping("/{id}")
    public Formation getOne(@PathVariable UUID id) {
        return formationService.getFormationById(id);
    }

    @GetMapping
    public List<Formation> getAll() {
        return formationService.getAllFormations();
    }

    @PutMapping("/{id}")
    public Formation update(@PathVariable UUID id, @RequestBody Formation formation) {
        return formationService.updateFormation(id, formation);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        formationService.deleteFormation(id);
    }
}
