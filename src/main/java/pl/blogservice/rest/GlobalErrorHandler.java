package pl.blogservice.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.blogservice.model.error.BlogPostError;

@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    //wbudowana klasa w Spring - na starcie aplikacji
    public ResponseEntity<BlogPostError> handleValidationError(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BlogPostError("Validation error", ex.getMessage()));
    }
}
