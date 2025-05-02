package cl.ntt.usercreation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.ntt.usercreation.dto.AuthRequestDTO;
import cl.ntt.usercreation.dto.AuthResponseDTO;
import cl.ntt.usercreation.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Autenticación", description = "Operaciones relacionadas con la autenticación de usuarios")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @Operation(summary = "Iniciar sesión", description = "Permite a un usuario iniciar sesión con su correo y contraseña")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login exitoso"),
            @ApiResponse(responseCode = "401", ref = "#/components/responses/401"),
            @ApiResponse(responseCode = "404", ref = "#/components/responses/404"),
            @ApiResponse(responseCode = "500", ref = "#/components/responses/500") })
    public AuthResponseDTO login(@Valid @RequestBody AuthRequestDTO authRequest) {
        String email = authRequest.getEmail();
        String password = authRequest.getPassword();
        return userService.sessionExists(email, password);
    }

}
