package sy.rf.demo.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sy.rf.demo.dto.EmployeurDto;
import sy.rf.demo.service.EmployeurService;
import java.util.List;

@RestController
@RequestMapping("/employeurs")
@SecurityRequirement(name = "bearerAuth")
public class EmployeurRestController {


    private final EmployeurService employeurService;
public EmployeurRestController(EmployeurService employeurService) {
    this.employeurService=employeurService;
}
    @PostMapping
    public ResponseEntity<EmployeurDto> create(@RequestBody EmployeurDto employeur) {
        EmployeurDto response= employeurService.createEmployeur(employeur);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
    if(id==null){
        return ResponseEntity.badRequest().body("No id given");
    }
        return ResponseEntity.ok(employeurService.getEmployeurById(id));
    }

    @GetMapping
    public ResponseEntity<List<EmployeurDto>> getAll() {
        return ResponseEntity.ok(employeurService.getAllEmployeurs());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody EmployeurDto employeur) {
if(id==null){
    return ResponseEntity.badRequest().body("No id was <provided");
}
if(employeur==null){
    return ResponseEntity.badRequest().body("No employeur was provided");
}

EmployeurDto employeurDto=EmployeurDto.builder()
        .id(id)
        .nomemployeur(employeur.getNomemployeur()!=null?employeur.getNomemployeur():employeurService.getEmployeurById(id).getNomemployeur())
        .build();
        EmployeurDto newEmployeur= employeurService.updateEmployeur( employeurDto);
        return ResponseEntity.ok(newEmployeur);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        employeurService.deleteEmployeur(id);
        return ResponseEntity.noContent().build();
    }
}
