package com.kingboot.basic.api;


import com.kingboot.common.model.RestResponse;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.pac4j.cas.profile.CasRestProfile;
import org.pac4j.core.context.J2EContext;
import org.pac4j.core.profile.ProfileManager;
import org.pac4j.jwt.config.encryption.SecretEncryptionConfiguration;
import org.pac4j.jwt.config.signature.SecretSignatureConfiguration;
import org.pac4j.jwt.credentials.authenticator.JwtAuthenticator;
import org.pac4j.jwt.profile.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;


@RestController
@RequestMapping ("/jwt")
@Api (description = "loginApi")
@Slf4j
public class LoginApi {
@Autowired
private JwtAuthenticator jwtAuthenticator;
    //需要设置安装jce $JAVA_HOME/jre/lib/security/java.security 设置crypto.policy=unlimited
    @Value ("${jwt.salt}")
    private String salt;
    
    @PostMapping("/token")
    public RestResponse<String> login(HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException {
        J2EContext context = new J2EContext(request, response);
        final ProfileManager<CasRestProfile> manager = new ProfileManager(context);
        final JwtGenerator generator = new JwtGenerator(new SecretSignatureConfiguration(salt),
                new SecretEncryptionConfiguration(salt));
        String token = "";
        final Optional<CasRestProfile> profile = manager.get(true);
        if (profile.isPresent()) {
            token = generator.generate(profile.get());
        }
        String ticketGrantingTicketId1 = profile.get().getTicketGrantingTicketId();
        return new RestResponse<>(token);
    }
}

