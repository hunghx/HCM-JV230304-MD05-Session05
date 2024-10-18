package ra.hunghx.sercurity.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
@Slf4j
@Component
public class JwtProvider { // Tao và giải mã token
@Value("${jwt.expired-date}")
private Long expiredDate;
@Value("${jwt.sercret-key}")
private String secretKey;
    // tạo token
    // validate token
    // giải mã token
    // tạo accessToken : 1 ngày
    public String generateAccessToken(String name) {
        Date today = new Date();
        return Jwts.builder().setSubject(name)// mã hóa username
                .setIssuedAt(today)
                .setExpiration(new Date(today.getTime() + expiredDate))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }
    public String generateRefreshToken(String name) { // 10 ngay
        Date today = new Date();
        return Jwts.builder().setSubject(name)// mã hóa username
                .setIssuedAt(today)
                .setExpiration(new Date(today.getTime() + expiredDate*10))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }
//    private Key getSigningKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }

    // validate token
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.error("JWT: message error expired:", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT: message error unsupported:", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("JWT: message error not formated:", e.getMessage());
        } catch (SignatureException e) {
            log.error("JWT: message error signature not math:", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT: message claims empty or argument invalid: ", e.getMessage());
        }
        return false;
    }

    // giải mã lây ra username
    public String getUserNameFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }
}
