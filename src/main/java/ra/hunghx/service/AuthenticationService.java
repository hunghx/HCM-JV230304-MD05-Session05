package ra.hunghx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ra.hunghx.dto.request.FormRegister;
import ra.hunghx.dto.response.JwtResponse;
import ra.hunghx.entity.RoleName;
import ra.hunghx.entity.Roles;
import ra.hunghx.entity.Users;
import ra.hunghx.repository.IRoleRepository;
import ra.hunghx.repository.IUserRepository;
import ra.hunghx.sercurity.jwt.JwtProvider;

import java.util.*;

@Service
public class AuthenticationService implements IAuthenticationService{
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private JwtProvider provider;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository;
    @Override
    public JwtResponse register(FormRegister request) {
        // quyeenf
        Set<Roles> roles = new HashSet<>();
        List<String> roleList = request.getRoles();
        if (roleList !=null && !roleList.isEmpty()){
            roleList.forEach(s -> {
                switch (s){
                    case "ROLE_ADMIN":
                        roles.add(roleRepository.findByRoleName(RoleName.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("id not found")));
                        case "ROLE_MANAGER":
                        roles.add(roleRepository.findByRoleName(RoleName.ROLE_MANAGER).orElseThrow(() -> new RuntimeException("id not found")));
                        case "ROLE_USER":
                        roles.add(roleRepository.findByRoleName(RoleName.ROLE_USER).orElseThrow(() -> new RuntimeException("id not found")));
                    default:
                        roles.add(roleRepository.findByRoleName(RoleName.ROLE_USER).orElseThrow(() -> new RuntimeException("id not found")));
                }
            });
        }else {
            roles.add(roleRepository.findByRoleName(RoleName.ROLE_USER).orElseThrow(() -> new RuntimeException("id not found")));
        }
        Users users = Users.builder()
                .email(request.getEmail())
                .fullName(request.getFullName())
                .password(encoder.encode(request.getPassword()))
                .address(request.getAddress())
                .sex(request.getSex())
                .phone(request.getPhone())
                .dateOfBirth(request.getDateOfBirth())
                .status(true)
                .rolesSet(roles)
                .build();
        Users userInfo = userRepository.save(users);
        Map<String, Object> map = new HashMap<>();
        map.put("id",userInfo.getId());
        map.put("fullName",userInfo.getFullName());
        map.put("status",userInfo.getStatus());
        map.put("role",userInfo.getRolesSet());
        // tra ve JWTResponse
        JwtResponse response = JwtResponse.builder()
                .accessToken(provider.generateAccessToken(userInfo.getEmail()))
                .refreshToken(provider.generateRefreshToken(userInfo.getEmail()))
                .user(map)
                .build();
        return response;
    }
}
