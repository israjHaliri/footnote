package com.haliri.israj.appservice;

import com.haliri.israj.appcore.utils.AppUtils;
import com.haliri.israj.appservice.config.CustomPasswordEncoderConfig;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppServiceTests {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void contextLoads() {
        AppUtils.getLogger(this).debug("generate password admin content : {}", passwordEncoder.encode("barberadmin"));
        AppUtils.getLogger(this).debug("generate password super admin : {}", passwordEncoder.encode("026"));

        CustomPasswordEncoderConfig customPasswordEncoder = new CustomPasswordEncoderConfig();
        String encoded = customPasswordEncoder.encode("MD12");
        Boolean decoded = customPasswordEncoder.matches("MD12", encoded);
        AppUtils.getLogger(this).debug("Custom encoded " + encoded);
        AppUtils.getLogger(this).debug("Custom encoded " + decoded);

        String hashed = BCrypt.hashpw("026", BCrypt.gensalt(12));
        AppUtils.getLogger(this).debug("genstalt 12 encoded " + hashed);
        String hashedBarber = BCrypt.hashpw("barberadmin", BCrypt.gensalt(12));
        AppUtils.getLogger(this).debug("genstalt 12 encoded " + hashedBarber);
    }

    @Test
    public void EncriptUtils() {
        String newPass = "026";
        byte[] bytesEncoded = Base64.encodeBase64(newPass.getBytes());
        AppUtils.getLogger(this).debug("ECNRYPT STRING : {}", new String(bytesEncoded));
    }

}
