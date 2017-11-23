package me.haliri.israj.appcore.repository.impl;

import me.haliri.israj.appcore.domain.BarberGuestBook;
import me.haliri.israj.appcore.domain.BarberTestimonial;
import me.haliri.israj.appcore.repository.BarberGuestBookRespository;
import me.haliri.israj.appcore.repository.BarberTestimonialRespository;
import me.haliri.israj.appcore.utils.AppUtils;
import org.apache.tomcat.jdbc.pool.DataSource;
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
@Transactional
public class BarberTestimonialRepositoryImpl implements BarberTestimonialRespository {

    @Autowired
    private DataSource dataSource;

    private final JdbcTemplate jdbcTemplate;

    public BarberTestimonialRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<BarberTestimonial> getListData() {
        return null;
    }

    @Override
    public BarberTestimonial getData() {
        return  null;
    }

    @Override
    public BarberTestimonial getDataById(Object id) {
        return null;
    }


    @Override
    @Transactional
    public void saveOrUpdate(BarberTestimonial barberGuestBook) {
    }

    @Override
    public BarberTestimonial getDataByParameters(Object parameters) {
        return null;
    }

    @Override
    public List<BarberTestimonial> getListDataByParameters(Object parameters) {
        Map<String,Object> param = (Map<String, Object>) parameters;
        List<BarberTestimonial> barberTestimonialList = new ArrayList<>();
        try {
            String sql = "SELECT t.*\n" +
                    "FROM\n" +
                    "(SELECT row_number() over() as rn,t.*\n" +
                    "FROM\n" +
                    "(SELECT t.*\n" +
                    "                   FROM\n" +
                    "                    (SELECT COUNT(id_testimonial) OVER() TOTAL_COUNT,id_testimonial,subject,description,age,create_date,update_date\n" +
                    "                    FROM barber.testimonial \n" +
                    "                    WHERE subject LIKE  '%"+param.get("search")+"%' \n" +
                    "                    ORDER BY barber.testimonial.id_testimonial DESC \n" +
                    "                    ) t\n" +
                    "                 ) t\n" +
                    "          ) t\n" +
                    "WHERE t.rn BETWEEN "+param.get("start")+"::integer AND "+param.get("length")+"::integer";

            barberTestimonialList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(BarberTestimonial.class));

        } catch (Exception e) {
            e.printStackTrace();
        }

        AppUtils.getLogger(this).debug("GET TESTIMONIAL LOG : {}", barberTestimonialList.toString());
        return barberTestimonialList;
    }

    @Override
    @Transactional
    public void saveData(BarberTestimonial barberGuestBook) {
        String sql = "INSERT INTO barber.testimonial\n" +
                "(subject, description, age, create_date, update_date)\n" +
                "VALUES (?, ?, ?, current_date, current_date)";
        jdbcTemplate.update(sql, barberGuestBook.getSubject(),barberGuestBook.getDescription(),barberGuestBook.getAge());
    }

    @Override
    @Transactional
    public void updateData(BarberTestimonial barberTestimonial) {
        String sql = "UPDATE barber.testimonial\n" +
                "SET subject=?, description=?, age=?, update_date=current_date\n" +
                "WHERE id_testimonial = ?";
        jdbcTemplate.update(sql, barberTestimonial.getSubject(),barberTestimonial.getDescription(),barberTestimonial.getAge(),barberTestimonial.getIdTestimonial());
    }

    @Override
    @Transactional
    public void deleteData(Object id) {
        String sql = "DELETE FROM barber.testimonial\n" +
                "WHERE id_testimonial = ?";
        jdbcTemplate.update(sql,id);
    }
}
