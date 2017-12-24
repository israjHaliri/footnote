package com.haliri.israj.appcore.common;

import com.haliri.israj.appcore.dao.common.UserDAO;
import com.haliri.israj.appcore.domain.common.Role;
import com.haliri.israj.appcore.domain.common.User;
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
public class UsersDAOTests {

	@Autowired
    UserDAO userRepository;

	@Test
	public void getDataById() throws SQLException {
		userRepository.getDataById("israj.haliri@gmail.com");
	}
}
