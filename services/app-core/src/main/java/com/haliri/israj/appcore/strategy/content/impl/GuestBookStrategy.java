package com.haliri.israj.appcore.strategy.content.impl;

import com.haliri.israj.appcore.domain.content.GuestBook;
import com.haliri.israj.appcore.strategy.content.DeleteDataStrategy;
import com.haliri.israj.appcore.strategy.content.GetDataStrategy;
import com.haliri.israj.appcore.strategy.content.SaveOrUpdateDataStrategy;
import com.haliri.israj.appcore.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by israjhaliri on 8/28/17.
 */
@Service
public class GuestBookStrategy {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private GetDataStrategy getDataStrategy;

    private SaveOrUpdateDataStrategy<GuestBook> saveOrUpdateDataStrategy;

    private DeleteDataStrategy<Integer> deleteDataStrategy;

    public List<GuestBook> getOneMonthListData() {
        getDataStrategy = (parameter) -> {
            List<GuestBook> guestBookList = new ArrayList<>();
            String sql = "SELECT guest_book.create_date, count(guest_book.create_date) total_count FROM barber.guest_book where guest_book.create_date BETWEEN\n" +
                    "NOW()::DATE-EXTRACT(DOW FROM NOW())::INTEGER-30\n" +
                    "AND NOW()::DATE-EXTRACT(DOW from NOW())::INTEGER group by guest_book.create_date";

            guestBookList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(GuestBook.class));
            AppUtils.getLogger(this).debug("GET ONE MONTH GUESTBOOK LOG : {}", guestBookList.toString());
            return guestBookList;
        };
        return (List<GuestBook>) getDataStrategy.process(null);
    }

    public List<GuestBook> getListData() {
        getDataStrategy = (parameter) -> {
            List<GuestBook> guestBookList = new ArrayList<>();
            String sql = "SELECT * FROM barber.guest_book ORDER BY id_guest_book ASC limit 5 ";

            guestBookList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(GuestBook.class));
            AppUtils.getLogger(this).debug("GET GUESTBOOK LOG : {}", guestBookList.toString());
            return guestBookList;
        };
        return (List<GuestBook>) getDataStrategy.process(null);
    }

    public List<GuestBook> getListDataById(Integer id) {
        getDataStrategy = (parameter) -> {
            List<GuestBook> guestBookList = new ArrayList<>();
            String sql = "SELECT * FROM barber.guest_book where id_guest_book = ? ";

            guestBookList = jdbcTemplate.query(sql,new Object[]{parameter}, new BeanPropertyRowMapper(GuestBook.class));
            AppUtils.getLogger(this).debug("GET BY ID GUESTBOOK LOG : {}", guestBookList.toString());
            return guestBookList;
        };
        return (List<GuestBook>) getDataStrategy.process(id);
    }

    public List<GuestBook> getListDataPerPage(Object allparameters) {
        getDataStrategy = (parameters) -> {
            Map<String, Object> param = (Map<String, Object>) parameters;
            List<GuestBook> guestBookList = new ArrayList<>();

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
            AppUtils.getLogger(this).debug("GET PERPAGE GUESTBOOK LOG : {}", guestBookList.toString());
            return guestBookList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(GuestBook.class));
        };
        return (List<GuestBook>) getDataStrategy.process(allparameters);
    }

    @Transactional
    public void saveData(GuestBook guestBook) {
        saveOrUpdateDataStrategy = (GuestBook parameters) -> {
            String sql = "INSERT INTO barber.guest_book (username, create_date) VALUES (?, current_date)";
            jdbcTemplate.update(sql, parameters.getUsername());
        };
        saveOrUpdateDataStrategy.process(guestBook);
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
