package com.mommyce.appcore.barber;

import com.mommyce.appcore.domain.barber.BarberTestimonial;
import com.mommyce.appcore.strategy.barber.impl.BarberTestimonialStrategy;
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
public class BarberTestimonialStrategyTests {

    @Autowired
    BarberTestimonialStrategy barberTestimonialStrategy;

    @Test
    public void getListDataPerPage() throws SQLException {
        Map<String,Object> param = new HashMap<>();
        param.put("start",1);
        param.put("length",10);
        param.put("search","jono");
        barberTestimonialStrategy.getListDataPerPage(param);
    }

    @Test
    public void saveData() throws SQLException {
        BarberTestimonial param = new BarberTestimonial();
        param.setAge(11);
        param.setSubject("jono");
        param.setDescription("testing");
        barberTestimonialStrategy.saveData(param);
    }

    @Test
    public void updateData() throws SQLException {
        BarberTestimonial param = new BarberTestimonial();
        param.setAge(11);
        param.setSubject("jono2");
        param.setDescription("testing2");
        param.setIdTestimonial(3);
        barberTestimonialStrategy.updateData(param);
    }

    @Test
    public void deleteData() throws SQLException {
        barberTestimonialStrategy.deleteData(3);
    }

    @Test
    public void getListData() throws SQLException {
        barberTestimonialStrategy.getListData();
    }
}
