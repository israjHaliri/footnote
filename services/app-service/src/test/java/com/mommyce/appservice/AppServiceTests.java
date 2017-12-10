package com.mommyce.appservice;

import com.mommyce.appcore.utils.AppUtils;
import com.mommyce.appservice.config.CustomPasswordEncoderConfig;
import com.mommyce.appservice.utils.AESUtils;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppServiceTests {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Test
	public void contextLoads() {
		AppUtils.getLogger(this).debug("generate password admin barber : {}",passwordEncoder.encode("barberadmin"));
		AppUtils.getLogger(this).debug("generate password super admin : {}",passwordEncoder.encode("026"));

		CustomPasswordEncoderConfig customPasswordEncoder = new CustomPasswordEncoderConfig();
		String encoded = customPasswordEncoder.encode("MD12");
		Boolean decoded = customPasswordEncoder.matches("MD12",encoded);
		AppUtils.getLogger(this).debug("Custom encoded "+encoded);
		AppUtils.getLogger(this).debug("Custom encoded "+decoded);
	}

	@Test
	public void EncriptUtils() {
		final String secretKey = "mommyce";

		String originalString = "026";
		String encryptedString = AESUtils.encrypt(originalString, secretKey) ;
		String decryptedString = AESUtils.decrypt(encryptedString, secretKey) ;

		AppUtils.getLogger(this).debug("ORIGINAL STRING : {}",originalString);
		AppUtils.getLogger(this).debug("ECNRYPT STRING : {}",encryptedString);
		AppUtils.getLogger(this).debug("DECRYPT STRING: {}",decryptedString);

		String newPass = "026";
		byte[]   bytesEncoded = Base64.encodeBase64(newPass.getBytes());
		AppUtils.getLogger(this).debug("ECNRYPT STRING : {}",new String(bytesEncoded ));
	}

}
