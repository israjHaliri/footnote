package me.haliri.israj.appcore;

import me.haliri.israj.appcore.domain.BarberGuestBook;
import me.haliri.israj.appcore.repository.BarberGuestBookRespository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BarberGuestBookRepositoryTests {

    @Autowired
    BarberGuestBookRespository barberGuestBookRespository;

    @Test
    public void getDataTests() throws SQLException {
        barberGuestBookRespository.getListData();
    }

    @Test
    public void inserBarberProfileTests() throws SQLException {
        BarberGuestBook param = new BarberGuestBook();
        param.setUsername("jono");
        barberGuestBookRespository.saveData(param);
    }
}
