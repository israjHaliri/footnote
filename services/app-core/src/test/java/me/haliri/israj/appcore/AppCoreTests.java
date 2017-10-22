package me.haliri.israj.appcore;

import me.haliri.israj.appcore.domain.User;
import me.haliri.israj.appcore.repository.UserRepository;
import me.haliri.israj.appcore.utils.AppUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppCoreTests {

	@Autowired
	UserRepository userRepository;

	@Test
	public void contextLoads() throws SQLException {
		User user = userRepository.findByUsername("JONO");
		AppUtils.getLogger(this).debug("USER DETAILS : {}",user.getId());
		AppUtils.getLogger(this).debug("USER DETAILS : {}",user.getRoles().toString());
		AppUtils.getLogger(this).debug("USER DETAILS : {}",user.getEnabled());
		AppUtils.getLogger(this).debug("USER DETAILS : {}",user.getPassword());
	}

}
