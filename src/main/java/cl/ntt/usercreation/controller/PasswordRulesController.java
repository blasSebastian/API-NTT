package cl.ntt.usercreation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.ntt.usercreation.entity.PasswordRules;
import cl.ntt.usercreation.service.PasswordRulesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/password")
@Tag(name = "Password", description = "Operaciones relacionadas con las reglas de contraseña")
public class PasswordRulesController {

    @Autowired
    private PasswordRulesService passwordRulesService;

    @GetMapping
    @Operation(summary = "Obtener reglas de contraseña", description = "Devuelve las reglas de contraseña configuradas")
    public PasswordRules getPasswordRules() {
        return passwordRulesService.getRules();
    }

    @PutMapping
    @Operation(summary = "Actualizar reglas de contraseña", description = "Actualiza las reglas de contraseña configuradas")
    public PasswordRules updatPasswordRules(@Valid @RequestBody PasswordRules passwordRules) {
        return passwordRulesService.updateRules(passwordRules);
    }

}
