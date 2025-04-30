package cl.ntt.usercreation.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.ntt.usercreation.entity.Phones;
import cl.ntt.usercreation.entity.User;
import cl.ntt.usercreation.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    // private static final Logger logger =
    // LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<User> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUsuarioById(@PathVariable UUID id) {
        Optional<User> usuario = usuarioRepository.findById(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public User createUsuario(@RequestBody User usuario) {
        usuario.setCreationDate(LocalDateTime.now());
        usuario.setModifDate(LocalDateTime.now());
        usuario.getPhones().setUser(usuario);
        return usuarioRepository.save(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUsuario(@PathVariable UUID id, @RequestBody User usuarioDetails) {
        Optional<User> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            User usuario = usuarioOptional.get();
            usuario.setName(usuarioDetails.getName());
            usuario.setEmail(usuarioDetails.getEmail());
            usuario.setPassword(usuarioDetails.getPassword());
            Phones phones = usuarioDetails.getPhones();
            phones.setUser(usuario);
            usuario.setPhones(phones);
            usuario.setModifDate(LocalDateTime.now());
            return ResponseEntity.ok(usuarioRepository.save(usuario));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> patchUsuario(@PathVariable UUID id, @RequestBody Map<String, Object> updates) {
        Optional<User> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            User usuario = usuarioOptional.get();

            // Actualizar los campos dinámicamente
            updates.forEach((key, value) -> {
                switch (key) {
                    case "nombre":
                        usuario.setName((String) value);
                        break;
                    case "correo":
                        usuario.setEmail((String) value);
                        break;
                    case "contraseña":
                        usuario.setPassword((String) value);
                        break;
                    case "telefonos":
                        @SuppressWarnings("unchecked")
                        Map<String, Object> phonesMap = (Map<String, Object>) value;
                        Phones phones = new Phones(usuario.getPhones().getId(),
                                (Integer) phonesMap.get("numero"),
                                (Integer) phonesMap.get("codigo_ciudad"),
                                (Integer) phonesMap.get("codigo_pais"), usuario);
                        usuario.setPhones(phones);
                        break;
                    case "fecha_hora_ultimo_login":
                        usuario.setLastLogin(LocalDateTime.now());
                        break;
                }
            });
            usuario.setModifDate(LocalDateTime.now());
            return ResponseEntity.ok(usuarioRepository.save(usuario));
        } else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable UUID id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}