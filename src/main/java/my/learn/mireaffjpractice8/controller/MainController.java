package my.learn.mireaffjpractice8.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("")
public class MainController {

    @GetMapping("/favicon.ico")
    public void getFavicon() {}


    @GetMapping("/health")
    public ResponseEntity<Object> getHealth() {
        Map<String, String> body = new HashMap<>();

        body.put("status", "ok");

        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
