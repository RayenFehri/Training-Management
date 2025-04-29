package sy.rf.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import sy.rf.demo.entity.User;
import sy.rf.demo.repository.UserRepository;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {


    @Autowired
    private UserRepository userRepository;

    // Utiliser une clé suffisamment longue et sécurisée pour HS512
    private final SecretKey SECRET = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private final long EXPIRATION_TIME = 864000; // 1 jour en ms

    public String generateToken(UserDetails userDetails) {
        // Récupérer l'utilisateur complet pour obtenir les informations
        User user = null;
        if (userDetails.getUsername() != null) {
            user = userRepository.findByEmail(userDetails.getUsername()).orElse(null);
        }

        JwtBuilder jwtBuilder = Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET, SignatureAlgorithm.HS512);

        // Ajoutez les informations si l'utilisateur existe
        if (user != null) {
            jwtBuilder.claim("nom", user.getNom());
            jwtBuilder.claim("prenom", user.getPrenom());
            jwtBuilder.claim("email", user.getEmail());
            jwtBuilder.claim("roleName", user.getRole().getNom());
        }

        return jwtBuilder.compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        return extractUsername(token).equals(userDetails.getUsername());
    }
}
