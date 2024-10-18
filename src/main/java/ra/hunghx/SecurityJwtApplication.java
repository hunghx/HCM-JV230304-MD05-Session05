package ra.hunghx;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ra.hunghx.entity.RoleName;
import ra.hunghx.entity.Roles;
import ra.hunghx.repository.IRoleRepository;

@SpringBootApplication
public class SecurityJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityJwtApplication.class, args);
    }
//    @Bean
//    public CommandLineRunner runner(IRoleRepository roleRepository){
//        return args -> {
//            // tao quyen
//            Roles admin = new Roles(null, RoleName.ROLE_ADMIN);
//            Roles ma = new Roles(null, RoleName.ROLE_MANAGER);
//            Roles u = new Roles(null, RoleName.ROLE_USER);
//            roleRepository.save(admin);
//            roleRepository.save(ma);
//            roleRepository.save(u);
//        };
//    }

}
