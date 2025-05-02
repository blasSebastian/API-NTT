package cl.ntt.usercreation.exception;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

import cl.ntt.usercreation.dto.ErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(UserCreationException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserCreationExceptionException(UserCreationException ex,
            HttpServletRequest request) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(ex.getMessage());
        return new ResponseEntity<>(errorResponse, ex.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationException(MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .findFirst()
                .orElse("Error de validación");

        ErrorResponseDTO errorResponse = new ErrorResponseDTO(errorMessage);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponseDTO> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException ex) {
        String errorMessage = String.format(
                "El valor '%s' no es válido para el parámetro '%s'. Se esperaba un valor de tipo '%s'",
                ex.getValue(), ex.getName(), ex.getRequiredType().getSimpleName());
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(errorMessage);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(UnrecognizedPropertyException.class)
    public ResponseEntity<Map<String, String>> handleUnrecognizedPropertyException(UnrecognizedPropertyException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Campo no permitido: " + ex.getPropertyName());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception ex,
            HttpServletRequest request) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO("Ocurrió un error inesperado, favor intente más tarde");
        logger.error("Error inesperado: ", ex);
        return ResponseEntity.status(500).body(errorResponse);
    }

}
