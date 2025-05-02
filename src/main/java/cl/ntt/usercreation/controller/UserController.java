package cl.ntt.usercreation.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.ntt.usercreation.dto.DeleteResponseDTO;
import cl.ntt.usercreation.dto.UserResponseDTO;
import cl.ntt.usercreation.entity.User;
import cl.ntt.usercreation.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UserController {

    // private static final Logger logger =
    // LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsuarios() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUsuarioById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public UserResponseDTO createUsuario(@Valid @RequestBody User usuario) {
        return userService.createUser(usuario);
    }

    @PutMapping("/{id}")
    public User updateUsuario(@PathVariable UUID id, @RequestBody User usuarioDetails) {
        return userService.updateUser(id, usuarioDetails);
    }

    @PatchMapping("/{id}")
    public User patchUsuario(@PathVariable UUID id, @RequestBody Map<String, Object> updates) {
        return userService.patchUser(id, updates);
    }

    @DeleteMapping("/{id}")
    public DeleteResponseDTO deleteUsuario(@PathVariable UUID id) {
        return userService.deleteUser(id);
    }
}