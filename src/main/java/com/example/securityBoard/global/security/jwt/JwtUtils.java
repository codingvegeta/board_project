package com.example.securityBoard.global.security.jwt;

import com.example.securityBoard.member.entity.Client;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtils {
    private final UserDetailsService userDetailsService;

    public String createToken(Client client) {
        Claims claims = Jwts.claims().setSubject(client.getAccountId());
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuer("callBusLab")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + JwtProperties.VALID_TIME))
                .signWith(SignatureAlgorithm.HS256, JwtProperties.SECRET_KEY)
                .compact();
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(JwtProperties.SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    public String getSubject(String token) {
        return Jwts.parser()
                .setSigningKey(JwtProperties.SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getSubject(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public boolean isValidToken(String token) {
        try {
            if (token == null) {
                return false;
            }
            Claims claims = getClaimsFromToken(token);
            return !claims.getExpiration().before(new Date());
        } catch (JwtException | NullPointerException exception) {
            return false;
        }
    }

    public String getExpiration(String token) {
        return String.valueOf(getClaimsFromToken(token).getExpiration());
    }
}
