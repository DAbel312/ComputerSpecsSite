package de.computerspecs.backend.controller;

import de.computerspecs.backend.dto.LoginRequestDTO;
import de.computerspecs.backend.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Login-Controller which gets information from front end for admin log in
 */

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    /**
     * takes request from front end and sends it to service
     * @param requestDTO request with username and password
     * @return
     */

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO requestDTO) {
        String username = requestDTO.getUsername();
        String password = requestDTO.getPassword();

        return loginService.login(password, username);
    }
}
