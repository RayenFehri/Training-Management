    package sy.rf.demo.service.impl;

    import org.springframework.security.core.context.SecurityContextHolder;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.stereotype.Service;
    import sy.rf.demo.dto.UserDto;
    import sy.rf.demo.entity.User;
    import sy.rf.demo.mappers.UserMapper;
    import sy.rf.demo.repository.UserRepository;
    import sy.rf.demo.service.UserService;

    import java.util.List;

    import java.util.stream.Collectors;

    @Service
    public class UserServiceImpl implements UserService {

      private final UserRepository userRepository;

        UserServiceImpl(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        @Override
        public UserDto addUser(UserDto userDto) {
            User user=User.builder()
                    .nom(userDto.getNom())
                    .prenom(userDto.getPrenom())
                    .email(userDto.getEmail())
                    .role(userDto.getRole())
                    .password(userDto.getPassword())
                            .build();
            return UserMapper.toDto(userRepository.save(user));
        }

        @Override
        public UserDto getUserById(Long id) {
            return  UserMapper.toDto(userRepository.findById(id).orElseThrow(()->new RuntimeException("User does not exist!")));
        }

        @Override
        public List<UserDto> getAllUsers() {
            return userRepository.findAll().stream().map(UserMapper::toDto).collect(Collectors.toList());
        }

        @Override
        public UserDto updateUser( UserDto userDto) {
            User user=User.builder()
                    .nom(userDto.getNom())
                    .prenom(userDto.getPrenom())
                    .email(userDto.getEmail())
                    .role(userDto.getRole())
                    .password(userDto.getPassword())
                    .build();
            return UserMapper.toDto(userRepository.save(user));
        }

        @Override
        public void deleteUser(Long id) {
            userRepository.deleteById(id);
        }
        @Override
        public UserDto getUserByEmail(String email) {
            return UserMapper.toDto( userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("User does not exist!")));
        }



        @Override
        public void logout() {
            SecurityContextHolder.clearContext();
        }

    }
