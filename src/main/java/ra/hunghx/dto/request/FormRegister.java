package ra.hunghx.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class FormRegister {
    private String email;
    private String password;
    private String confirmPassword;
    private String fullName;
    private String phone;
    private Boolean sex;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;
    private String address;
    private List<String> roles;
}
