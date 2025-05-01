package sy.rf.demo.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sy.rf.demo.dto.StructureDto;
import sy.rf.demo.service.StructureService;

import java.util.List;

@RestController
@RequestMapping("/structures")
@SecurityRequirement(name = "bearerAuth")
public class StructureRestController {

    private final StructureService structureService;

    public StructureRestController(StructureService structureService) {
        this.structureService = structureService;
    }

    @PostMapping
    public ResponseEntity<StructureDto> create(@RequestBody StructureDto structureDto) {
        structureDto.setId(null);
        return ResponseEntity.ok().body(structureService.createStructure(structureDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StructureDto> getOne(@PathVariable Long id) {
        StructureDto structureDto = structureService.getStructureById(id);
        if (structureDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(structureDto);
    }

    @GetMapping
    public ResponseEntity<List<StructureDto>> getAll() {
        return ResponseEntity.ok().body(structureService.getAllStructures());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody StructureDto structureDto) {
        if (id == null) {
            return ResponseEntity.badRequest().body("id must not be null");
        }
        if (structureDto == null) {
            return ResponseEntity.badRequest().body("structure must not be null");
        }

        StructureDto existingStructure = structureService.getStructureById(id);
        if (existingStructure == null) {
            return ResponseEntity.badRequest().body("structure not found");
        }
        structureDto.setId(id);
        return ResponseEntity.ok().body(structureService.updateStructure(structureDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        structureService.deleteStructure(id);
        return ResponseEntity.noContent().build();
    }
}