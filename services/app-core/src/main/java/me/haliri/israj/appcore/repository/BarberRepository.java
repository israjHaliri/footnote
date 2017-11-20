package me.haliri.israj.appcore.repository;

import me.haliri.israj.appcore.domain.Role;
import me.haliri.israj.appcore.domain.User;
import me.haliri.israj.appcore.utils.AppUtils;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by israjhaliri on 8/28/17.
 */
@Repository
public class BarberRepository {

    @Autowired
    private DataSource dataSource;

    public List<Map<String,Object>> getAllGuest(){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "SELECT * FROM barber.guest_book";

        List<Map<String,Object>> listData = jdbcTemplate.queryForList(sql);
        AppUtils.getLogger(this).debug("GET ALL GUEST LOG : {}",listData.toString());

        return listData;

    }

    public List<Map<String,Object>> getProfile(){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "SELECT id_profile, address, hand_phone, phone, email, lat, \"long\", create_date, update_date\n" +
                "FROM barber.profile;";

        List<Map<String,Object>> listData = jdbcTemplate.queryForList(sql);
        AppUtils.getLogger(this).debug("GET PROFILE LOG : {}",listData.toString());

        return listData;

    }
}
