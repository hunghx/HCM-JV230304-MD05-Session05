package ra.hunghx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ra.hunghx.dto.request.FormLogin;
import ra.hunghx.dto.request.FormRegister;
import ra.hunghx.dto.response.JwtResponse;
import ra.hunghx.service.IAuthenticationService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    @Autowired
    private IAuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<JwtResponse> register(@RequestBody FormRegister request){
       JwtResponse response = authenticationService.register(request);
       return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody FormLogin reuqest){
        return new ResponseEntity<>(authenticationService.login(reuqest), HttpStatus.CREATED);
    }
}
