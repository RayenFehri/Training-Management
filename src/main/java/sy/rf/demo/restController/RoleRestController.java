package sy.rf.demo.restController;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sy.rf.demo.entity.Role;
import sy.rf.demo.service.RoleService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class RoleRestController {


    @Autowired
      RoleService roleService;

    @PostMapping
    public Role create(@RequestBody Role role) {
        return roleService.createRole(role);
    }

    @GetMapping("/{id}")
    public Role getOne(@PathVariable UUID id) {
        return roleService.getRoleById(id);
    }

    @GetMapping
    public List<Role> getAll() {
        return roleService.getAllRoles();
    }

    @PutMapping("/{id}")
    public Role update(@PathVariable UUID id, @RequestBody Role role) {
        return roleService.updateRole(id, role);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        roleService.deleteRole(id);
    }
}
