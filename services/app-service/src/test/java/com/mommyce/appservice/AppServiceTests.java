package com.mommyce.appservice;

import com.mommyce.appcore.utils.AppUtils;
import com.mommyce.appservice.utils.AESUtils;
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
	}

}
