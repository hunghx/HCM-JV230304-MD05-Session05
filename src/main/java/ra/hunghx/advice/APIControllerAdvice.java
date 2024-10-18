package ra.hunghx.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class APIControllerAdvice {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handle(RuntimeException e){
        return  new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
