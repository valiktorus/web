package by.gsu.epamlab.exceptions;


public class UserAuthenticationException extends Exception {
    public UserAuthenticationException() {
    }

    public UserAuthenticationException(String message) {
        super(message);
    }

    public UserAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
