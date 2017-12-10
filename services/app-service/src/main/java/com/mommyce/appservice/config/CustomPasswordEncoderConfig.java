package com.mommyce.appservice.config;

import org.apache.commons.codec.binary.Base64;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by israjhaliri on 12/10/17.
 */
@Component
public class CustomPasswordEncoderConfig implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        byte[] decodedPassword= Base64.decodeBase64(rawPassword.toString().getBytes());
        String hashed = BCrypt.hashpw(new String(decodedPassword), BCrypt.gensalt(12));
        return hashed;
    }
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        byte[] decodedPassword= Base64.decodeBase64(rawPassword.toString().getBytes());
        return BCrypt.checkpw(new String(decodedPassword), encodedPassword);
    }
}
