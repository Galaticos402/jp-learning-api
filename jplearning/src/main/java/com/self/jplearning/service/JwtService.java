package com.self.jplearning.service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.self.jplearning.config.property.CognitoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class JwtService {

    @Autowired
    private CognitoProperties cognitoProperties;

    private final Logger logger = Logger.getLogger(this.getClass().getName());


    public JWTClaimsSet getClaimSet(String token) throws ParseException {
        SignedJWT jwt = SignedJWT.parse(token);
        return jwt.getJWTClaimsSet();
    }
    public boolean validateToken(String token) {
        final String JWKS_URL = String.format("https://cognito-idp.us-east-1.amazonaws.com/%s/.well-known/jwks.json", cognitoProperties.getUserPoolId());
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
            if (!issuer.equals(String.format("https://cognito-idp.us-east-1.amazonaws.com/%s", cognitoProperties.getUserPoolId()))) {
                return false;
            }
            boolean isTokenValid = !claims.getExpirationTime().before(new java.util.Date());
            return isTokenValid;

        } catch (Exception e) {
            logger.info(e.getMessage());
            return false;
        }
    }


}
