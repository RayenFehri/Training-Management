package sy.rf.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import sy.rf.demo.entity.Role;
import sy.rf.demo.security.AuthEntryPointJwt;
import sy.rf.demo.security.AuthTokenFilter;

import java.util.Arrays;

@Configuration
@EnableMethodSecurity
public class SecurityConfig  {
    private final AuthEntryPointJwt unauthorizedHandler;

    @Autowired
    public SecurityConfig(
            AuthEntryPointJwt unauthorizedHandler) {
        this.unauthorizedHandler = unauthorizedHandler;
    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "Accept"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling.authenticationEntryPoint(unauthorizedHandler)
                )
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                // Public endpoints
                                .requestMatchers(
                                        "/auth/**",
                                        "/swagger-ui/**",
                                        "/v3/api-docs/**"
                                ).permitAll()
                                .requestMatchers("/structures/**")
                                .hasAnyRole(Role.SIMPLE.name(), Role.ADMIN.name(), Role.MANAGER.name())
                                .requestMatchers("/domaines/**")
                                .hasAnyRole(Role.SIMPLE.name(), Role.ADMIN.name(), Role.MANAGER.name())
                                .requestMatchers("/employeurs/**")
                                .hasAnyRole(Role.SIMPLE.name(), Role.ADMIN.name(), Role.MANAGER.name())
                                .requestMatchers("/formateurs/**")
                                .hasAnyRole(Role.SIMPLE.name(), Role.ADMIN.name(), Role.MANAGER.name())
                                .requestMatchers("/formations/**")
                                .hasAnyRole(Role.SIMPLE.name(), Role.ADMIN.name(), Role.MANAGER.name())
                                .requestMatchers("/participants/**")
                                .hasAnyRole(Role.SIMPLE.name(), Role.ADMIN.name(), Role.MANAGER.name())
                                .requestMatchers("/profils/**")
                                .hasAnyRole(Role.SIMPLE.name(), Role.ADMIN.name(), Role.MANAGER.name())
                                .requestMatchers("/users/**")
                                .hasAnyRole(Role.SIMPLE.name(), Role.ADMIN.name(), Role.MANAGER.name())
                                .requestMatchers("/auth/logout")
                                .hasAnyRole(Role.SIMPLE.name(), Role.ADMIN.name(), Role.MANAGER.name())
                                .anyRequest().authenticated()
                );

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}