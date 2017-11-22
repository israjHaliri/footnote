package me.haliri.israj.appcore;

import me.haliri.israj.appcore.domain.BarberProfile;
import me.haliri.israj.appcore.domain.Role;
import me.haliri.israj.appcore.domain.User;
import me.haliri.israj.appcore.repository.BarberProfileRespository;
import me.haliri.israj.appcore.repository.UserRespository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BarberProfileRepositoryTests {

	@Autowired
	BarberProfileRespository barberProfileRespository;

	@Test
	public void getDataTests() throws SQLException {
		barberProfileRespository.getData();
	}

	@Test
	public void inserBarberProfileTests() throws SQLException {
		BarberProfile barberProfile = new BarberProfile();
		barberProfile.setAddress("jln goal para");
		barberProfile.setEmail("admin.barber@gmail.com");
		barberProfile.setLat(new Float(-6.121435));
		barberProfile.setLon(new Float(106.774124));
		barberProfile.setPhone("+6285862624149");
		barberProfileRespository.saveOrUpdate(barberProfile);
	}
}
