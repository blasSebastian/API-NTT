package cl.ntt.usercreation.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import cl.ntt.usercreation.dto.AuthResponseDTO;
import cl.ntt.usercreation.dto.DeleteResponseDTO;
import cl.ntt.usercreation.dto.UserResponseDTO;
import cl.ntt.usercreation.entity.Phones;
import cl.ntt.usercreation.entity.User;
import cl.ntt.usercreation.exception.UserCreationException;
import cl.ntt.usercreation.repository.UserRepository;
import cl.ntt.usercreation.util.JwtUtil;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserCreationException("Usuario no encontrado", HttpStatus.NOT_FOUND));
    }

    public UserResponseDTO createUser(User user) {
        isEmailExists(user.getEmail());

        user.setCreationDate(LocalDateTime.now());
        user.setModifDate(LocalDateTime.now());
        user.setActive(true);
        String token = generateToken(user.getEmail());
        user.setToken(token);

        if (user.getPhones() != null) {
            user.getPhones().forEach(phone -> phone.setUser(user));
        }
        User savedUser = userRepository.save(user);

        return new UserResponseDTO(savedUser.getId(), savedUser.getCreationDate(), savedUser.getModifDate(),
                savedUser.getLastLogin(), token, savedUser.isActive());
    }

    public DeleteResponseDTO deleteUser(UUID id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return new DeleteResponseDTO("Usuario eliminado con éxito", id.toString());
        } else {
            throw new UserCreationException("Usuario no encontrado", HttpStatus.NOT_FOUND);
        }
    }

    public User updateUser(UUID id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserCreationException("Usuario no encontrado", HttpStatus.NOT_FOUND));

        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        user.setActive(userDetails.isActive());
        user.setModifDate(LocalDateTime.now());

        if (userDetails.getPhones() != null) {
            userDetails.getPhones().forEach(phone -> phone.setUser(user));
            user.setPhones(userDetails.getPhones());
        }

        return userRepository.save(user);
    }

    public User patchUser(UUID id, Map<String, Object> updates) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserCreationException("Usuario no encontrado", HttpStatus.NOT_FOUND));

        updates.forEach((key, value) -> {
            switch (key) {
                case "nombre":
                    user.setName((String) value);
                    break;
                case "correo":
                    user.setEmail((String) value);
                    break;
                case "contraseña":
                    user.setPassword((String) value);
                    break;
                case "activo":
                    user.setActive((Boolean) value);
                    break;
                case "telefonos":
                    if (value instanceof List) {
                        @SuppressWarnings("unchecked")
                        List<Phones> phones = (List<Phones>) value;
                        phones.forEach(phone -> phone.setUser(user));
                        user.setPhones(phones);
                    } else {
                        throw new UserCreationException("El campo 'telefonos' debe ser una lista",
                                HttpStatus.BAD_REQUEST);
                    }
                    break;
                default:
                    throw new UserCreationException("Campo " + key + " no es válido ", HttpStatus.BAD_REQUEST);
            }
        });

        user.setModifDate(LocalDateTime.now());
        return userRepository.save(user);
    }

    private void isEmailExists(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new UserCreationException("El correo ya está registrado", HttpStatus.BAD_REQUEST);
        }
    }

    public AuthResponseDTO sessionExists(String email, String password) {

        User user = userRepository.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new UserCreationException("Credenciales inválidas", HttpStatus.UNAUTHORIZED));

        String token = generateToken(email);
        user.setToken(token);
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);
        return new AuthResponseDTO(token, LocalDateTime.now());
    }

    public String generateToken(String username) {
        return jwtUtil.generateToken(username);
    }

    public String validateToken(String token) {
        return jwtUtil.validateToken(token);
    }

}
