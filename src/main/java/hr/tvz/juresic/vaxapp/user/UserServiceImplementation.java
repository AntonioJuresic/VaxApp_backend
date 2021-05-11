package hr.tvz.juresic.vaxapp.user;

import hr.tvz.juresic.vaxapp.authority.Authority;
import org.springframework.stereotype.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService
{
    private final UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO findUserByUsername(String username) {
        return userRepository.findOneByUsername(username).stream().findFirst().map(this::mapUsersToDTO).orElse(null);
    }

    private UserDTO mapUsersToDTO(final User user) {
        List<String> authorities = user.getAuthorities().stream().map(Authority::getName).collect(Collectors.toList());
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                authorities
        );
    }
}
