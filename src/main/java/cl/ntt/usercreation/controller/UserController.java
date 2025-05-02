package cl.ntt.usercreation.controller;

import java.util.List;
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
import cl.ntt.usercreation.dto.UserCreateRequestDTO;
import cl.ntt.usercreation.dto.UserCreateResponseDTO;
import cl.ntt.usercreation.dto.UserPatchDTO;
import cl.ntt.usercreation.entity.User;
import cl.ntt.usercreation.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuarios", description = "Operaciones relacionadas con los usuarios")
public class UserController {

    // private static final Logger logger =
    // LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private UserService userService;

    @GetMapping
    @Operation(summary = "Obtener todos los usuarios", description = "Devuelve todos los usuarios y sus campos registrados")
    public List<User> getAllUsuarios() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener usuario por ID", description = "Devuelve un usuario específico por su ID")
    // @ApiResponses(value = {
    // @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
    // @ApiResponse(responseCode = "404", ref = "#/components/responses/404"),
    // @ApiResponse(responseCode = "500", ref = "#/components/responses/500") })
    public User getUsuarioById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo usuario", description = "Crea un nuevo usuario y devuelve su información")
    public UserCreateResponseDTO createUsuario(@Valid @RequestBody UserCreateRequestDTO usuario) {
        return userService.createUser(usuario);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar usuario", description = "Actualiza la información de un usuario existente")
    public User updateUsuario(@PathVariable UUID id, @RequestBody User usuarioDetails) {
        return userService.updateUser(id, usuarioDetails);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Actualizar parcialmente un usuario", description = "Actualiza parcialmente la información de un usuario existente")
    public User patchUsuario(@PathVariable UUID id, @Valid @RequestBody UserPatchDTO updates) {
        return userService.patchUser(id, updates);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar usuario", description = "Elimina un usuario por su ID")
    public DeleteResponseDTO deleteUsuario(@PathVariable UUID id) {
        return userService.deleteUser(id);
    }
}