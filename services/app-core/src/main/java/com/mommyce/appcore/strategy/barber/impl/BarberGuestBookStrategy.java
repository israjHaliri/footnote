package com.mommyce.appcore.strategy.barber.impl;

import com.mommyce.appcore.domain.barber.BarberTestimonial;
import com.mommyce.appcore.strategy.barber.DeleteDataStrategy;
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
import java.util.Map;

/**
 * Created by israjhaliri on 8/28/17.
 */
@Service
public class BarberGuestBookStrategy {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private GetDataStrategy getDataStrategy;

    private SaveOrUpdateDataStrategy<BarberGuestBook> saveOrUpdateDataStrategy;

    private DeleteDataStrategy<Integer> deleteDataStrategy;

    public List<BarberGuestBook> getOneMonthListData() {
        getDataStrategy = (parameter) -> {
            List<BarberGuestBook> barberGuestBookList = new ArrayList<>();
            String sql = "SELECT guest_book.create_date, count(guest_book.create_date) total_count FROM barber.guest_book where guest_book.create_date BETWEEN\n" +
                    "NOW()::DATE-EXTRACT(DOW FROM NOW())::INTEGER-30\n" +
                    "AND NOW()::DATE-EXTRACT(DOW from NOW())::INTEGER group by guest_book.create_date";

            barberGuestBookList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(BarberGuestBook.class));
            AppUtils.getLogger(this).debug("GET ONE MONTH GUESTBOOK LOG : {}", barberGuestBookList.toString());
            return barberGuestBookList;
        };
        return (List<BarberGuestBook>) getDataStrategy.process(null);
    }

    public List<BarberGuestBook> getListData() {
        getDataStrategy = (parameter) -> {
            List<BarberGuestBook> barberGuestBookList = new ArrayList<>();
            String sql = "SELECT * FROM barber.guest_book ORDER BY id_guest_book ASC limit 5 ";

            barberGuestBookList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(BarberGuestBook.class));
            AppUtils.getLogger(this).debug("GET GUESTBOOK LOG : {}", barberGuestBookList.toString());
            return barberGuestBookList;
        };
        return (List<BarberGuestBook>) getDataStrategy.process(null);
    }

    public List<BarberGuestBook> getListDataById(Integer id) {
        getDataStrategy = (parameter) -> {
            List<BarberGuestBook> barberGuestBookList = new ArrayList<>();
            String sql = "SELECT * FROM barber.guest_book where id_guest_book = ? ";

            barberGuestBookList = jdbcTemplate.query(sql,new Object[]{parameter}, new BeanPropertyRowMapper(BarberGuestBook.class));
            AppUtils.getLogger(this).debug("GET BY ID GUESTBOOK LOG : {}", barberGuestBookList.toString());
            return barberGuestBookList;
        };
        return (List<BarberGuestBook>) getDataStrategy.process(id);
    }

    public List<BarberGuestBook> getListDataPerPage(Object allparameters) {
        getDataStrategy = (parameters) -> {
            Map<String, Object> param = (Map<String, Object>) parameters;
            List<BarberGuestBook> barberGuestBookList = new ArrayList<>();

            String sql = "SELECT t.*\n" +
                    "FROM\n" +
                    "   (SELECT row_number() over() as rn,t.*\n" +
                    "       FROM\n" +
                    "           (SELECT t.*\n" +
                    "                   FROM\n" +
                    "                    (SELECT COUNT(id_guest_book) OVER() TOTAL_COUNT,id_guest_book,username,create_date\n" +
                    "                    FROM barber.guest_book \n" +
                    "                    WHERE username LIKE  '%" + param.get("search") + "%' \n" +
                    "                    ORDER BY barber.guest_book.id_guest_book DESC \n" +
                    "                    ) t\n" +
                    "                 ) t\n" +
                    "          ) t\n" +
                    "WHERE t.rn BETWEEN " + param.get("start") + "::integer AND " + param.get("length") + "::integer";
            AppUtils.getLogger(this).debug("GET PERPAGE GUESTBOOK LOG : {}", barberGuestBookList.toString());
            return barberGuestBookList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(BarberGuestBook.class));
        };
        return (List<BarberGuestBook>) getDataStrategy.process(allparameters);
    }

    @Transactional
    public void saveData(BarberGuestBook barberGuestBook) {
        saveOrUpdateDataStrategy = (BarberGuestBook parameters) -> {
            String sql = "INSERT INTO barber.guest_book (username, create_date) VALUES (?, current_date)";
            jdbcTemplate.update(sql, parameters.getUsername());
        };
        saveOrUpdateDataStrategy.process(barberGuestBook);
    }

    @Transactional
    public void deleteData(Integer id) {
        deleteDataStrategy = (parameters) -> {
            String sql = "DELETE FROM barber.guest_book\n" +
                    "WHERE id_guest_book = ?";
            jdbcTemplate.update(sql, parameters);
        };
        deleteDataStrategy.process(id);
    }
}
