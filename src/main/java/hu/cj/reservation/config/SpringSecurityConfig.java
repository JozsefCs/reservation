package hu.cj.reservation.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    public static final String USER = "user";

    @Autowired
    private JwtConverter jwtConverter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authz) ->
                authz.requestMatchers(HttpMethod.GET, "/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/login/**" ).permitAll()
                        .requestMatchers(HttpMethod.POST,"/company/**" ).hasRole(USER)
                        .requestMatchers(HttpMethod.DELETE,"/company/**" ).hasRole(USER)
                        .requestMatchers(HttpMethod.POST,"/service/**" ).hasRole(USER)
                        .requestMatchers(HttpMethod.DELETE,"/service/**" ).hasRole(USER)
                        .requestMatchers(HttpMethod.POST,"/reservation/**" ).hasRole(USER)
                        .requestMatchers(HttpMethod.DELETE,"/reservation/**" ).hasRole(USER)
                        .anyRequest().authenticated());

        http.sessionManagement(sess -> sess.sessionCreationPolicy(
                SessionCreationPolicy.STATELESS));
        http.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtConverter)));

        return http.build();
    }
}
