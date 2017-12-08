package com.mommyce.appservice;

import com.mommyce.appcore.utils.AppUtils;
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

}
