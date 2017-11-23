package me.haliri.israj.appcore;

import me.haliri.israj.appcore.domain.BarberGuestBook;
import me.haliri.israj.appcore.domain.BarberTestimonial;
import me.haliri.israj.appcore.repository.BarberGuestBookRespository;
import me.haliri.israj.appcore.repository.BarberTestimonialRespository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BarberTestimonialRepositoryTests {

    @Autowired
    BarberTestimonialRespository barberTestimonialRespository;

    @Test
    public void getListDataByParametersTests() throws SQLException {
        Map<String,Object> param = new HashMap<>();
        param.put("start",1);
        param.put("length",10);
        param.put("search","jono");
        barberTestimonialRespository.getListDataByParameters(param);
    }

    @Test
    public void saveData() throws SQLException {
        BarberTestimonial param = new BarberTestimonial();
        param.setAge(11);
        param.setSubject("jono");
        param.setDescription("testing");
        barberTestimonialRespository.saveData(param);
    }

    @Test
    public void updateData() throws SQLException {
        BarberTestimonial param = new BarberTestimonial();
        param.setAge(11);
        param.setSubject("jono2");
        param.setDescription("testing2");
        param.setIdTestimonial(3);
        barberTestimonialRespository.updateData(param);
    }

    @Test
    public void deleteData() throws SQLException {
        barberTestimonialRespository.deleteData(3);
    }
}
