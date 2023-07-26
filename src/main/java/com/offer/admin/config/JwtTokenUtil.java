package com.offer.admin.config;


import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.function.Function;

import com.offer.admin.entity.UserSettings;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import
        org.springframework.stereotype.Component;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import
        io.jsonwebtoken.SignatureAlgorithm;

import static com.offer.admin.config.Constants.ACCESS_TOKEN_VALIDITY_SECONDS;


@Component
public class JwtTokenUtil implements Serializable {

    private static final byte[] SIGNING_KEY = null;

    public String getUsernameFromToken(String token) {
        return
                getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return
                getClaimFromToken(token, Claims::getExpiration);
    }

    public String getUserIdFromToken(String token) {
        Claims claims =
                getAllClaimsFromToken(token);

        String s = (String) claims.get("userId");

        return s;

    }


    public <T> T getClaimFromToken(String token, Function<Claims, T>
            claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return
                claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SIGNING_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration =
                getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(UserSettings user) {
        return
                doGenerateToken(user.getEmail());
    }

    private String doGenerateToken(String subject) {

        Claims claims = Jwts.claims().setSubject(subject);
        claims.put("scopes",
                Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
        claims.put("userId", "user000001");
        //claims.put("OrgId", "org000001");

        return Jwts.builder().setClaims(claims).setIssuer("http://offertedeals.com")
                .setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new
                        Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS * 1000))
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {

        System.out.println("userDetails####################   " + userDetails.toString(
        ));

        final String username = getUsernameFromToken(token);
        return (
                username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
