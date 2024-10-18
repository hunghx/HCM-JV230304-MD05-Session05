package ra.hunghx.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Map;
@Builder
@Data
public class JwtResponse {
    private final String type = "Bearer Token";
    private String accessToken;
    private String refreshToken;
    private Map<String,Object> user;
}
