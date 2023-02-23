package com.example.imagehelper.config;


import com.example.imagehelper.auth.AuthenticationProvidedHelper;
import com.example.imagehelper.auth.RsaKeyProperties;
import com.example.imagehelper.service.UserService;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final UserService userService;
    private final RsaKeyProperties rsaKeyProperties;

    @Autowired
    public SecurityConfiguration(UserService userService, RsaKeyProperties rsaKeyProperties) {
        this.userService = userService;
        this.rsaKeyProperties = rsaKeyProperties;

    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        return new AuthenticationProvidedHelper(userService);
    }
    /**
     * Secure REST endpoints
     *
     * @param http
     * @return
     * @throws Exception
     */

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.ignoringAntMatchers("/api/auth/**"))
                .authorizeRequests(auth -> auth.antMatchers("/api/auth/**").permitAll().anyRequest().authenticated())
                .authenticationProvider(authenticationProvider())
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .formLogin(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }




    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(rsaKeyProperties.getPublicKey()).build();
    }

    @Bean
    public JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder((rsaKeyProperties.getPublicKey())).privateKey(rsaKeyProperties.getPrivateKey()).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
