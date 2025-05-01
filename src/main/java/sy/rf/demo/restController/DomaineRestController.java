package sy.rf.demo.restController;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sy.rf.demo.entity.Domaine;
import sy.rf.demo.service.DomaineService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/domaines")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class DomaineRestController {

    @Autowired
      DomaineService domaineService;

    @PostMapping
    public Domaine create(@RequestBody Domaine domaine) {
        return domaineService.createDomaine(domaine);
    }

    @GetMapping("/{id}")
    public Domaine getOne(@PathVariable UUID id) {
        return domaineService.getDomaineById(id);
    }

    @GetMapping
    public List<Domaine> getAll() {
        return domaineService.getAllDomaines();
    }

    @PutMapping("/{id}")
    public Domaine update(@PathVariable UUID id, @RequestBody Domaine domaine) {
        return domaineService.updateDomaine(id, domaine);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        domaineService.deleteDomaine(id);
    }
}

