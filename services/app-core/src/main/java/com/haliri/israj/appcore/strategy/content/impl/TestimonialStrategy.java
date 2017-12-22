package com.haliri.israj.appcore.strategy.content.impl;

import com.haliri.israj.appcore.domain.content.Testimonial;
import com.haliri.israj.appcore.handler.impl.ResponseHandlerImpl;
import com.haliri.israj.appcore.strategy.content.DeleteDataStrategy;
import com.haliri.israj.appcore.strategy.content.GetDataStrategy;
import com.haliri.israj.appcore.strategy.content.SaveOrUpdateDataStrategy;
import com.haliri.israj.appcore.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by israjhaliri on 8/28/17.
 */
@Repository
public class TestimonialStrategy {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private GetDataStrategy getDataStrategy;

    private SaveOrUpdateDataStrategy<Testimonial> saveOrUpdateDataStrategy;

    private DeleteDataStrategy<Integer> deleteDataStrategy;

    @Autowired
    ResponseHandlerImpl responseHandlerImpl;

    public List<Testimonial> getListData() {
        getDataStrategy = (parameters) -> {
            List<Testimonial> testimonialList = new ArrayList<>();

            String sql = "SELECT * FROM barber.testimonial ORDER BY create_date DESC LIMIT 5";

            testimonialList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Testimonial.class));
            AppUtils.getLogger(this).debug("TESTIMONIAL LOG GET DATA: {}", testimonialList.toString());
            return testimonialList;
        };
        return (List<Testimonial>) getDataStrategy.process(null);
    }

    public List<Testimonial> getListDataById(Integer id) {
        getDataStrategy = (parameters) -> {
            List<Testimonial> testimonialList = new ArrayList<>();

            String sql = "SELECT * FROM barber.testimonial where id_testimonial = ?";

            testimonialList = jdbcTemplate.query(sql,new Object[]{parameters}, new BeanPropertyRowMapper(Testimonial.class));
            AppUtils.getLogger(this).debug("TESTIMONIAL LOG GET DATA: {}", testimonialList.toString());
            return testimonialList;
        };
        return (List<Testimonial>) getDataStrategy.process(id);
    }

    public List<Testimonial> getListDataPerPage(Object allparameters) {
        getDataStrategy = (parameters) -> {
            Map<String, Object> param = (Map<String, Object>) parameters;
            List<Testimonial> testimonialList = new ArrayList<>();

            String sql = "SELECT t.*\n" +
                    "FROM\n" +
                    "   (SELECT row_number() over() as rn,t.*\n" +
                    "       FROM\n" +
                    "           (SELECT t.*\n" +
                    "                   FROM\n" +
                    "                    (SELECT COUNT(id_testimonial) OVER() TOTAL_COUNT,id_testimonial,subject,description,age,create_date,update_date\n" +
                    "                    FROM barber.testimonial \n" +
                    "                    WHERE subject LIKE  '%" + param.get("search") + "%' \n" +
                    "                    ORDER BY barber.testimonial.id_testimonial DESC \n" +
                    "                    ) t\n" +
                    "                 ) t\n" +
                    "          ) t\n" +
                    "WHERE t.rn BETWEEN " + param.get("start") + "::integer AND " + param.get("length") + "::integer";
            AppUtils.getLogger(this).debug("GET TESTIMONIAL LOG : {}", testimonialList.toString());
            return testimonialList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Testimonial.class));
        };
        return (List<Testimonial>) getDataStrategy.process(allparameters);
    }


    @Transactional
    public void saveData(Testimonial barberGuestBook) {
        saveOrUpdateDataStrategy = (Testimonial parameters) -> {
            String sql = "INSERT INTO barber.testimonial\n" +
                    "(subject, description, age, create_date, update_date)\n" +
                    "VALUES (?, ?, ?, current_date, current_date)";
            jdbcTemplate.update(sql, parameters.getSubject(), parameters.getDescription(), parameters.getAge());
        };
        saveOrUpdateDataStrategy.process(barberGuestBook);
    }

    @Transactional
    public void updateData(Testimonial testimonial) {
        saveOrUpdateDataStrategy = (Testimonial parameters) -> {
            String sql = "UPDATE barber.testimonial\n" +
                    "SET subject=?, description=?, age=?, update_date=current_date\n" +
                    "WHERE id_testimonial = ?";
            jdbcTemplate.update(sql, parameters.getSubject(), parameters.getDescription(), parameters.getAge(), parameters.getIdTestimonial());
        };
        saveOrUpdateDataStrategy.process(testimonial);
    }

    @Transactional
    public void deleteData(Integer id) {
        deleteDataStrategy = (parameters) -> {
            String sql = "DELETE FROM barber.testimonial\n" +
                    "WHERE id_testimonial = ?";
            jdbcTemplate.update(sql, parameters);
        };
        deleteDataStrategy.process(id);
    }
}
