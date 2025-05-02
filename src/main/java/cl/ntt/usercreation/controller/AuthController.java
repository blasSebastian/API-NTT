package cl.ntt.usercreation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.ntt.usercreation.dto.AuthRequestDTO;
import cl.ntt.usercreation.dto.AuthResponseDTO;
import cl.ntt.usercreation.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public AuthResponseDTO login(@Valid @RequestBody AuthRequestDTO authRequest) {
        String email = authRequest.getEmail();
        String password = authRequest.getPassword();
        return userService.sessionExists(email, password);
    }

}
