package sy.rf.demo.service;

import sy.rf.demo.dto.UserDto;
import java.util.List;

public interface UserService {
    UserDto addUser(UserDto user);
    UserDto getUserById(Long id);
    List<UserDto> getAllUsers();
    UserDto updateUser(UserDto user);
    void deleteUser(Long id);
    UserDto getUserByEmail(String email);
    void logout();
}

