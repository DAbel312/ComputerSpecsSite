package de.computerspecs.backend.service;

import de.computerspecs.backend.entity.LoginResponse;
import de.computerspecs.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * login Service
 */

@Service
public class LoginService {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtService jwtService;

    /**
     * checks if password and username are correct and starts generation of jwt
     * if correct, it sends token back to front-end, if not correct unauthorized http status
     * @param password
     * @param username
     * @return
     */

    public ResponseEntity<?> login(String password, String username) {
        var optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            var user = optionalUser.get();

            if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
                String id = String.valueOf(user.getId());
                String token = jwtService.generateJWT(id, "computerspecs", username, 100 * 60 * 15 * 15);

                return ResponseEntity.ok(new LoginResponse(token));
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

}
