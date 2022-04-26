package az.iktlab.securityservice.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ErrorResponse handleUsernameNotFoundException(UserNotFoundException ex){
        return response(ex,HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler
    public ErrorResponse handleWrongFieldException(WrongFieldException ex){
        return response(ex,HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler
    public ErrorResponse duplicateFieldException(DuplicateFieldException ex){
        return response(ex,HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(SignatureException.class)
    public ErrorResponse signature() {
        return new ErrorResponse("Token is invalid", HttpStatus.UNAUTHORIZED.value(),LocalDateTime.now());
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ErrorResponse time() {
        return new ErrorResponse("Token is expired",HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
    }

    private <T extends RuntimeException> ErrorResponse  response(T exception,int statusCode){
        return new ErrorResponse(exception.getMessage(),statusCode,LocalDateTime.now());
    }
}
