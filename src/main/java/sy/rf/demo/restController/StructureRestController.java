package sy.rf.demo.restController;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sy.rf.demo.entity.Structure;
import sy.rf.demo.service.StructureService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/structures")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class StructureRestController {

    @Autowired
    private StructureService structureService;

    @PostMapping
    public Structure create(@RequestBody Structure structure) {
        return structureService.createStructure(structure);
    }

    @GetMapping("/{id}")
    public Structure getOne(@PathVariable UUID id) {
        return structureService.getStructureById(id);
    }

    @GetMapping
    public List<Structure> getAll() {
        return structureService.getAllStructures();
    }

    @PutMapping("/{id}")
    public Structure update(@PathVariable UUID id, @RequestBody Structure structure) {
        return structureService.updateStructure(id, structure);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        structureService.deleteStructure(id);
    }
}
