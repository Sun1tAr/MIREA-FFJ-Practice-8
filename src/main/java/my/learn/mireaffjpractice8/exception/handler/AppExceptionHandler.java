package my.learn.mireaffjpractice8.exception.handler;

import my.learn.mireaffjpractice8.exception.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> handleAppException(AppException e) {
        Map<String, String> body = new HashMap<>();
        body.put("message", e.getMessage());
        body.put("status", e.getStatus().getReasonPhrase());
        return new ResponseEntity<>(body, e.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleException(Exception e) {
        Map<String, String> body = new HashMap<>();
        body.put("message", e.getMessage());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
