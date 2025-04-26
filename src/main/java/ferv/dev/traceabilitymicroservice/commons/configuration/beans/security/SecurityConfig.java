package ferv.dev.traceabilitymicroservice.commons.configuration.beans.security;

import ferv.dev.traceabilitymicroservice.category.domain.models.enums.Role;
import ferv.dev.traceabilitymicroservice.category.infrastructure.security.filters.TokenAuthenticationFilter;
import ferv.dev.traceabilitymicroservice.commons.configuration.utils.constants.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final TokenAuthenticationFilter tokenAuthenticationFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(authorize -> authorize
                                //.requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                                .requestMatchers(ApiPaths.ORDER_TRACE + ApiPaths.CREATE).hasRole(Role.CLIENT.name())
                                .requestMatchers(ApiPaths.ORDER_TRACE + ApiPaths.EMPLOYEE).hasRole(Role.EMPLOYEE.name())
                                .requestMatchers(ApiPaths.ORDER_TRACE + ApiPaths.UPDATE).hasAnyRole(Role.CLIENT.name(), Role.EMPLOYEE.name())
                                .requestMatchers(ApiPaths.ORDER_TRACE + ApiPaths.ID).hasRole(Role.CLIENT.name())
                                .anyRequest().authenticated()
                )
                .addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

}