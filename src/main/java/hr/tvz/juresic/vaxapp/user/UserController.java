package hr.tvz.juresic.vaxapp.user;

import hr.tvz.juresic.vaxapp.security.SecurityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserServiceImplementation userServiceImplementation;

    public UserController(UserServiceImplementation userServiceImplementation) {
        this.userServiceImplementation = userServiceImplementation;
    }

    @GetMapping("/current-user")
    public ResponseEntity<UserDTO> getCurrentUser() {
        Optional<String> username = SecurityUtils.getCurrentUserUsername();

        if(username.isPresent()) {
            System.out.println("------------------------------------------");
            System.out.println(SecurityUtils.getCurrentUserJWT());
            System.out.println("------------------------------------------");

            UserDTO userDTO = userServiceImplementation.findUserByUsername(username.get());

            if(userDTO != null) {
                return ResponseEntity.status(HttpStatus.OK).body(userDTO);
            }
        }
        return ResponseEntity.notFound().build();
    }
}
