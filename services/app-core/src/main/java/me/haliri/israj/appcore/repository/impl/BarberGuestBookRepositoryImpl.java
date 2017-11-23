package me.haliri.israj.appcore.repository.impl;

import me.haliri.israj.appcore.domain.BarberGuestBook;
import me.haliri.israj.appcore.repository.BarberGuestBookRespository;
import me.haliri.israj.appcore.utils.AppUtils;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by israjhaliri on 8/28/17.
 */
@Repository
@Transactional
public class BarberGuestBookRepositoryImpl implements BarberGuestBookRespository {

    @Autowired
    private DataSource dataSource;

    private final JdbcTemplate jdbcTemplate;

    public BarberGuestBookRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<BarberGuestBook> getListDataByParameters(Object parameters) {
        return null;
    }

    @Override
    public List<BarberGuestBook> getListData() {
        List<BarberGuestBook> barberGuestBookList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM barber.guest_book\n" +
                    "ORDER BY id_guest_book ASC ";

            barberGuestBookList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(BarberGuestBook.class));

        } catch (Exception e) {
            e.printStackTrace();
        }

        AppUtils.getLogger(this).debug("GET GUESTBOOK LOG : {}", barberGuestBookList.toString());
        return barberGuestBookList;
    }

    @Override
    public BarberGuestBook getData() {
        return  null;
    }

    @Override
    public BarberGuestBook getDataById(Object id) {
        return null;
    }


    @Override
    @Transactional
    public void saveOrUpdate(BarberGuestBook barberGuestBook) {
    }

    @Override
    public BarberGuestBook getDataByParameters(Object parameters) {
        return null;
    }

    @Override
    public void saveData(BarberGuestBook barberGuestBook) {
        String sql = "INSERT INTO barber.guest_book\n" +
                "(username, create_date)\n" +
                "VALUES (?, current_date);";
        jdbcTemplate.update(sql, barberGuestBook.getUsername());
    }

    @Override
    public void updateData(BarberGuestBook barberGuestBook) {

    }

    @Override
    public void deleteData(Object id) {

    }
}
