package com.mommyce.appcore.barber;

import com.mommyce.appcore.domain.barber.BarberGuestBook;
import com.mommyce.appcore.strategy.barber.impl.BarberGuestBookStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BarberGuestBookStrategyTests {

    @Autowired
    BarberGuestBookStrategy barberGuestBookStrategy;

    @Test
    public void getData() throws SQLException {
        barberGuestBookStrategy.getListData();
    }

    @Test
    public void inserBarberProfile() throws SQLException {
        BarberGuestBook param = new BarberGuestBook();
        param.setUsername("jono");
        barberGuestBookStrategy.saveData(param);
    }
}
