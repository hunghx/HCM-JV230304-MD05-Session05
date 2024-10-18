package ra.hunghx.controller;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class TestController {
    @GetMapping("/admin/test") // quyen admin
    public ResponseEntity<String> admin(){
        return new ResponseEntity<>("succcess", HttpStatus.OK);
    }
    @GetMapping("/user/test") // quyen admin
    public ResponseEntity<String> user(){
        return new ResponseEntity<>("succcess", HttpStatus.OK);
    }
}
