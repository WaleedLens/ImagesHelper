package com.example.imagehelper.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class TokenService {

    private final JwtEncoder jwtEncoder;

    public TokenService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    /**
     * Generate Bearer Token / JWT Token
     * @param authentication
     * @return
     */
    public String generateToken(Authentication authentication) {
        Instant instant = Instant.now();
        String scope = "";
        for(GrantedAuthority grantedAuthority:authentication.getAuthorities()){
            scope = scope + grantedAuthority.getAuthority() + " ";
        }

        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(instant)
                .expiresAt(instant.plus(1, ChronoUnit.HOURS)) //make token expires after 1 hour
                .subject(authentication.getName())
                .claim("scope",scope).build();
        return this.jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
    }
}
