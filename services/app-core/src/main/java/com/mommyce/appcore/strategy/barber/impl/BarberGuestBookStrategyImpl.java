package com.mommyce.appcore.strategy.barber.impl;

import com.mommyce.appcore.domain.barber.BarberTestimonial;
import com.mommyce.appcore.strategy.barber.GetDataStrategy;
import com.mommyce.appcore.domain.barber.BarberGuestBook;
import com.mommyce.appcore.strategy.barber.SaveOrUpdateDataStrategy;
import com.mommyce.appcore.utils.AppUtils;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by israjhaliri on 8/28/17.
 */
@Service
public class BarberGuestBookStrategyImpl {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private GetDataStrategy getDataStrategy;

    private SaveOrUpdateDataStrategy<BarberGuestBook> saveOrUpdateDataStrategy;

    public List<BarberGuestBook> getListData() {
        getDataStrategy = (parameter) -> {
            List<BarberGuestBook> barberGuestBookList = new ArrayList<>();
            String sql = "SELECT * FROM barber.guest_book ORDER BY id_guest_book ASC ";
            try {
                barberGuestBookList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(BarberGuestBook.class));
            } catch (Exception e) {
                e.printStackTrace();
                AppUtils.getLogger(this).error("ERROR GUESTBOOK LOG GET LIST DATA: {}", e.getMessage());
            }
            AppUtils.getLogger(this).debug("GET GUESTBOOK LOG : {}", barberGuestBookList.toString());
            return barberGuestBookList;
        };
        return (List<BarberGuestBook>) getDataStrategy.process(null);
    }

    @Transactional
    public void saveData(BarberGuestBook barberGuestBook) {
        saveOrUpdateDataStrategy = (BarberGuestBook parameters) ->{
            String sql = "INSERT INTO barber.guest_book (username, create_date) VALUES (?, current_date)";
            jdbcTemplate.update(sql, parameters.getUsername());
        };
    }
}
