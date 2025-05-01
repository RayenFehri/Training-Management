package sy.rf.demo.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sy.rf.demo.dto.DomaineDto;
import sy.rf.demo.entity.Domaine;
import sy.rf.demo.service.DomaineService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/domaines")
@SecurityRequirement(name = "bearerAuth")
public class DomaineRestController {

    private final  DomaineService domaineService;
    public DomaineRestController(DomaineService domaineService) {
        this.domaineService = domaineService;
    }

    @PostMapping
    public ResponseEntity<DomaineDto> create(@RequestBody DomaineDto domaine) {
        DomaineDto response= domaineService.createDomaine(domaine);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DomaineDto> getOne(@PathVariable Long id) {
        DomaineDto response= domaineService.getDomaineById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<DomaineDto>> getAll() {
        List<DomaineDto> response= domaineService.getAllDomaines();
        return  ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody DomaineDto domaine) {
        if(id==null){
            return ResponseEntity.badRequest().body("NUll id provided");
        }
        if(domaine==null){
            return ResponseEntity.badRequest().body("Domaine provided");
        }
        DomaineDto oldDomaine= domaineService.getDomaineById(id);
        DomaineDto domaineDto=DomaineDto.builder()
                .id(id)
                .libelle(domaine.getLibelle()==null?oldDomaine.getLibelle():domaine.getLibelle())
                .build();
        DomaineDto updated= domaineService.updateDomaine( domaineDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        domaineService.deleteDomaine(id);
        return ResponseEntity.noContent().build();
    }
}

