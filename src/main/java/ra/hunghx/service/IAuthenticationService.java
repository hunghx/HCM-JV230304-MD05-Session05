package ra.hunghx.service;

import ra.hunghx.dto.request.FormLogin;
import ra.hunghx.dto.request.FormRegister;
import ra.hunghx.dto.response.JwtResponse;

public interface IAuthenticationService {
    JwtResponse register(FormRegister request);
    JwtResponse login(FormLogin request);
}
