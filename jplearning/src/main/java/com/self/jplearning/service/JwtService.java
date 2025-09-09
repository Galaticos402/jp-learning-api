package com.self.jplearning.service;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.stereotype.Service;

import java.net.URL;

@Service
public class JwtService {
    private static final String JWKS_URL = "https://cognito-idp.us-east-1.amazonaws.com/us-east-1_Lyfa5g0T6/.well-known/jwks.json";
    public boolean validateToken(String token) {
        try {
            JWKSet jwkSet = JWKSet.load(new URL(JWKS_URL));
            SignedJWT jwt = SignedJWT.parse(token);

            // Get the key ID from token header
            String kid = jwt.getHeader().getKeyID();

            // Find the key in JWKS
            JWK jwk = jwkSet.getKeyByKeyId(kid);
            if (jwk == null) {
                return false;
            }

            // Verify signature
            boolean valid = jwt.verify(new com.nimbusds.jose.crypto.RSASSAVerifier(jwk.toRSAKey()));
            if (!valid) {
                return false;
            }

            // Validate claims (issuer, expiration, audience, etc.)
            JWTClaimsSet claims = jwt.getJWTClaimsSet();

            String issuer = claims.getIssuer();
            if (!issuer.equals("https://cognito-idp.us-east-1.amazonaws.com/us-east-1_Lyfa5g0T6")) {
                return false;
            }
            return !claims.getExpirationTime().before(new java.util.Date());

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
