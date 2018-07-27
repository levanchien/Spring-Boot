package com.lvc.springbootrestjpabasicauth.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class TokenAuthenticationService {
    private static final long EXPIRATION_TIME = 864_000_000; // 10 days

    private static final String SECRET = "ThisIsASecret";

    private static final String TOKEN_PREFIX = "Bearer";

    private static final String HEADER_STRING = "Authorization";

    public static void addAuthentication(HttpServletResponse response, String username) {
        String jwt = Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + jwt);
    }

    public static Optional<Authentication> getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            String username = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();
            log.info("Token: " + token);
            log.info("UserName: " + username);
            return username != null ? Optional.of(new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList())) : Optional.empty();

        }
        return Optional.empty();
    }

    public static String token(String username) {
        return Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();
    }
}
