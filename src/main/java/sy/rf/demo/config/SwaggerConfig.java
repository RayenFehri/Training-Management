package sy.rf.demo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;


@OpenAPIDefinition(
        info = @Info(title = "Training Center Management API"
                ,version = "1.0",
                description = "All endpoints follow RESTful conventions and require appropriate authentication where necessary."
                                ,contact = @Contact(name = "Rayen Fehri", email = "rayen.fehri@etudiant-isi.utm.tn")
        ),
        servers = @Server(url = "http://localhost:8080",description = "Local Server")
)
@Configuration
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class SwaggerConfig {
}