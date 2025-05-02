package cl.ntt.usercreation.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class UserCreationException extends RuntimeException {

    private final HttpStatus status;

    public UserCreationException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
