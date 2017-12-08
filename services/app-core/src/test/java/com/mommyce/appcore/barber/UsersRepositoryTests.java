package com.mommyce.appcore.barber;

import com.mommyce.appcore.domain.common.Role;
import com.mommyce.appcore.domain.common.User;
import com.mommyce.appcore.dao.common.UserDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersRepositoryTests {

	@Autowired
    UserDAO userRepository;

	@Test
	public void getDataByIdTests() throws SQLException {
		userRepository.getDataById("israj.haliri@gmail.com");
	}

	@Test
	public void inserUserTests() throws SQLException {
		List<Role> roles = new ArrayList<>();
		roles.add(new Role("5","ROLE_ADMIN","test@gmail.com"));
		userRepository.saveData(new User("test@gmail.com","$2a$10$8k2lhhix.vOEJSSioh2.KedJxICTU07hY4NFd4NURAEVdc3JtoZCa",true,roles));
	}
}
