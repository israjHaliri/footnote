package me.haliri.israj.appcore;

import me.haliri.israj.appcore.domain.User;
import me.haliri.israj.appcore.repository.UserRepository;
import me.haliri.israj.appcore.utils.AppUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersRepositoryTests {

	@Autowired
	UserRepository userRepository;

	@Test
	public void finByUsernameTest() throws SQLException {
		User user = userRepository.findByUsername("israj.haliri@gmail.com");
		AppUtils.getLogger(this).debug("USER DETAILS : {}",user.getId());
		AppUtils.getLogger(this).debug("USER DETAILS : {}",user.getRoles().toString());
		AppUtils.getLogger(this).debug("USER DETAILS : {}",user.getEnabled());
		AppUtils.getLogger(this).debug("USER DETAILS : {}",user.getPassword());
	}

	@Test
	public void inserUserTests() throws SQLException {
		userRepository.insertUser("israj.haliri@gmail.com","$2a$10$8k2lhhix.vOEJSSioh2.KedJxICTU07hY4NFd4NURAEVdc3JtoZCa",true,"ROLE_SUPER_ADMIN");
		userRepository.insertUser("barber.admin@gmail.com","$2a$10$GrjqzqPYTidufPK7kbjet.sbY7q19UnT3FdQXSITODJ.kL/HCDwUG",true,"ROLE_BARBER_ADMIN");
	}



}
