package com.mommyce.appcore.barber;

import com.mommyce.appcore.domain.barber.BarberProfile;
import com.mommyce.appcore.strategy.barber.impl.BarberProfileStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BarberProfileRepositoryTests {

	@Autowired
	BarberProfileStrategy barberProfileStrategy;

	@Test
	public void getDataTests() throws SQLException {
		barberProfileStrategy.getData();
	}

	@Test
	public void inserBarberProfileTests() throws SQLException {
		BarberProfile barberProfile = new BarberProfile();
		barberProfile.setAddress("jln goal para");
		barberProfile.setEmail("admin.barber@gmail.com");
		barberProfile.setLat(new Float(-6.121435));
		barberProfile.setLon(new Float(106.774124));
		barberProfile.setPhone("+6285862624149");
		barberProfileStrategy.saveOrUpdate(barberProfile);
	}
}
