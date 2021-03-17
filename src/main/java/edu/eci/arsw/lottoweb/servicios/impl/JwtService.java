package edu.eci.arsw.lottoweb.servicios.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import edu.eci.arsw.lottoweb.servicios.*;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * ---------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------------------------------------------------------------------------------------------
 * 													CLASE: JwtService
 * ---------------------------------------------------------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------------------------------------------------------
 * @author Santiago Buitrago
 * @author Adriana Catañeda
 * @author Guillermo Castro
 * @version 1.0
 * ---------------------------------------------------------------------------------------------------------------------------
 */

@Service
public class JwtService {
    public static final String BEARER = "Bearer ";

    private static final String USER = "user";
    private static final String ROLES = "roles";
    private static final String ISSUER = "miw-spring5";
    private static final int EXPIRES_IN_MILLISECOND = 3600000;
    private static final String SECRET = "clave-secreta-test";


    public String createToken(String user, List<String> roles) {
        return JWT.create()
                .withIssuer(ISSUER)
                .withIssuedAt(new Date())
                .withNotBefore(new Date())
//                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRES_IN_MILLISECOND))
                .withClaim(USER, user)
                .withArrayClaim(ROLES, roles.toArray(new String[0]))
                .sign(Algorithm.HMAC256(SECRET));
    }

    public boolean isBearer(String authorization) {
        System.out.println(authorization + " SISAS");
//        if(authorization != null) System.out.println(authorization.startsWith(BEARER));
        return authorization != null && authorization.startsWith(BEARER) && authorization.split("\\.").length == 3;
    }

    public String user(String authorization) {
//        System.out.println(authorization+" ????/////////");
        return this.verify(authorization).getClaim(USER).asString();
    }

    private DecodedJWT verify(String authorization) {
        System.out.println(authorization);
        if (!this.isBearer(authorization)) {
            throw new JwtException("It is not Berear");
        }
        try {
            return JWT.require(Algorithm.HMAC256(SECRET))
                    .withIssuer(ISSUER).build()
                    .verify(authorization.substring(BEARER.length()));
        } catch (Exception exception) {
            throw new JwtException("JWT is wrong. " + exception.getMessage());
        }
    }

    public List<String> roles(String authorization) {
        return Arrays.asList(this.verify(authorization).getClaim(ROLES).asArray(String.class));
    }
}
