package cl.ntt.usercreation.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import cl.ntt.usercreation.dto.AuthResponseDTO;
import cl.ntt.usercreation.dto.DeleteResponseDTO;
import cl.ntt.usercreation.dto.UserCreateRequestDTO;
import cl.ntt.usercreation.dto.UserCreateResponseDTO;
import cl.ntt.usercreation.dto.UserPatchDTO;
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

    @Autowired
    private PasswordRulesService passwordRulesService;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserCreationException("Usuario no encontrado", HttpStatus.NOT_FOUND));
    }

    public UserCreateResponseDTO createUser(UserCreateRequestDTO userRequest) {
        isEmailExists(userRequest.getEmail());
        validatePassword(userRequest.getPassword());
        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());

        List<Phones> phones = userRequest.getPhone();

        if (phones != null) {
            phones.forEach(phone -> {
                phone.setUser(user);
            });
            user.setPhones(phones);
        }
        // Set default values
        user.setCreationDate(LocalDateTime.now());
        user.setModifDate(LocalDateTime.now());
        user.setActive(true);
        String token = generateToken(user.getEmail());
        user.setToken(token);

        User savedUser = userRepository.save(user);

        return new UserCreateResponseDTO(savedUser.getId(), savedUser.getCreationDate(), savedUser.getModifDate(),
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
        isEmailExists(userDetails.getEmail());
        validatePassword(userDetails.getPassword());

        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        user.setActive(userDetails.isActive());
        user.setModifDate(LocalDateTime.now());

        if (userDetails.getPhones() != null) {
            List<Phones> existingPhones = user.getPhones();
            List<Phones> newPhones = userDetails.getPhones();

            newPhones.forEach(phone -> {
                phone.setUser(user);
                if (!existingPhones.contains(phone)) {
                    existingPhones.add(phone);
                }
            });
            user.setPhones(existingPhones);
        }

        userRepository.save(user);
        return user;
    }

    public User patchUser(UUID id, UserPatchDTO userPatch) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserCreationException("Usuario no encontrado", HttpStatus.NOT_FOUND));

        if (userPatch.getName() != null) {
            user.setName(userPatch.getName());
        }

        if (userPatch.getEmail() != null) {
            isEmailExists(userPatch.getEmail());
            user.setEmail(userPatch.getEmail());
        }

        if (userPatch.getPassword() != null) {
            validatePassword(userPatch.getPassword());
            user.setPassword(userPatch.getPassword());
        }

        if (userPatch.getActive() != null) {
            user.setActive(userPatch.getActive());
        }

        if (userPatch.getPhones() != null) {

            if (userPatch.getPhones().isEmpty())
                throw new UserCreationException("Lista de teléfonos no puede estar vacía", HttpStatus.BAD_REQUEST);

            List<Phones> existingPhones = user.getPhones();
            List<Phones> newPhones = userPatch.getPhones();

            newPhones.forEach(phone -> {
                phone.setUser(user);
                if (!existingPhones.contains(phone)) {
                    existingPhones.add(phone);
                }
            });
            user.setPhones(existingPhones);
        }

        user.setModifDate(LocalDateTime.now());
        return userRepository.save(user);
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

    private void isEmailExists(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new UserCreationException("Correo ya está registrado", HttpStatus.BAD_REQUEST);
        }
    }

    private void validatePassword(String password) {
        if (!passwordRulesService.validatePassword(password)) {
            String message = passwordRulesService.getRules().toString();

            throw new UserCreationException("Contraseña no cumple con las siguientes reglas: " + message,
                    HttpStatus.BAD_REQUEST);
        }
    }

}
