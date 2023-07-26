package com.offer.admin.config;


//import com.korero.offertedeals.service.UserService;
import com.offer.admin.DTO.TokenRes;
import com.offer.admin.entity.UserSettings;
import com.offer.admin.repository.UserSettingsRepository;
import com.offer.admin.service.AuthenticationService;
import io.jsonwebtoken.*;
import
        org.springframework.beans.factory.annotation.Autowired;
import
        org.springframework.security.authentication.
                UsernamePasswordAuthenticationToken;
import
        org.springframework.security.core.Authentication;
import
        org.springframework.security.core.GrantedAuthority;
import
        org.springframework.security.core.authority.SimpleGrantedAuthority;
import
        org.springframework.security.core.userdetails.UserDetails;
import
        org.springframework.stereotype.Component;



import java.io.Serializable;
import java.util.Arrays;
import
        java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import
        java.util.function.Function;
import java.util.stream.Collectors;

import static com.offer.admin.config.Constants.*;

@Component
public class TokenProvider implements Serializable {


    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    UserSettingsRepository userSettingsRepository;

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

    public String getOrgIdFromToken(String token) {
        Claims claims =
                getAllClaimsFromToken(token);
        String s = (String) claims.get("orgId");
        return
                s;
    }

    public String getRoleFromToken(String token) {
        Claims claims =
                getAllClaimsFromToken(token);
        String s = (String) claims.get("scopes");
        return
                s;
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

    public TokenRes generateToken(Authentication authentication) {
        System.out.println("inside generate token...");
        final String authorities =
                authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                        .collect(Collectors.joining(","));


        Claims claims = Jwts.claims().setSubject(authentication.getName());



            UserSettings user = userSettingsRepository.findByEmail(authentication.getName());
            claims.put("scopes", authorities);
            claims.put("userId",user.getUserId() );



            String tokenString = Jwts.builder()
                .setSubject(authentication.getName())
                // .claim(AUTHORITIES_KEY, authorities)
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS*1000))
                .compact();
            
            TokenRes result = new TokenRes();
            result.setToken(tokenString);
            result.setType(user.getUserType());
			return result;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (
                username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    UsernamePasswordAuthenticationToken getAuthentication(final String token,
            final Authentication existingAuth, final UserDetails userDetails) {

final JwtParser jwtParser = Jwts.parser().setSigningKey(SIGNING_KEY);

final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);

final Claims claims = claimsJws.getBody();

final Collection<? extends GrantedAuthority> authorities = Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
.map(SimpleGrantedAuthority::new).collect(Collectors.toList());

return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
}


}