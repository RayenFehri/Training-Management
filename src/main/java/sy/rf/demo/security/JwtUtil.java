package sy.rf.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sy.rf.demo.dto.UserDto;
import sy.rf.demo.entity.Role;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}") // Doit être en millisecondes
    private Long jwtExpiration;

    private SecretKey secretKey;

    @PostConstruct
    public void init() {
        // Vérification que la clé secrète a une longueur suffisante
        if (jwtSecret.length() < 32) {
            throw new IllegalStateException("JWT secret key must be at least 32 characters long");
        }
        this.secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(UserDto user) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpiration);

        System.out.println("Generating token:");
        System.out.println("Current time: " + now);
        System.out.println("Expiry time: " + expiryDate);
        System.out.println("Expiration duration (ms): " + jwtExpiration);

        return Jwts.builder()
                .subject(user.getId().toString())
                .claim("username", user.getPrenom())
                .claim("nom", user.getNom())
                .claim("email", user.getEmail())
                .claim("role", user.getRole().name())
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(secretKey, Jwts.SIG.HS256)
                .compact();
    }

    public UserDto extractUserDetails(String token) throws JwtException {
        try {
            var claims = Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            return UserDto.builder()
                    .id(Long.parseLong(claims.getSubject()))
                    .prenom(claims.get("username", String.class))
                    .nom(claims.get("nom", String.class))
                    .email(claims.get("email", String.class))
                    .role(Role.valueOf(claims.get("role", String.class)))
                    .build();
        } catch (JwtException e) {
            System.err.println("Error extracting user details from token: " + e.getMessage());
            throw e;
        }
    }

    public boolean validateJwtToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            // Vérification explicite de la date d'expiration
            if (claims.getExpiration().before(new Date())) {
                System.out.println("Token expired at: " + claims.getExpiration());
                return false;
            }

            return true;
        } catch (ExpiredJwtException ex) {
            System.err.println("Token expired: " + ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            System.err.println("Unsupported JWT: " + ex.getMessage());
        } catch (MalformedJwtException ex) {
            System.err.println("Malformed JWT: " + ex.getMessage());
        } catch (SecurityException ex) {
            System.err.println("Security exception: " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            System.err.println("Illegal argument: " + ex.getMessage());
        }
        return false;
    }

    // Nouvelle méthode pour vérifier le temps restant
    public long getRemainingValidity(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getExpiration().getTime() - System.currentTimeMillis();
    }
}