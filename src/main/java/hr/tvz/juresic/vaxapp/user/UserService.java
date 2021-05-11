package hr.tvz.juresic.vaxapp.user;

public interface UserService {
    UserDTO findUserByUsername(String username);
}
