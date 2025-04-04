package sy.rf.demo.service;

import sy.rf.demo.entity.Role;

import java.util.List;
import java.util.UUID;

public interface RoleService {
    Role createRole(Role role);
    Role getRoleById(UUID id);
    List<Role> getAllRoles();
    Role updateRole(UUID id, Role role);
    void deleteRole(UUID id);
}
