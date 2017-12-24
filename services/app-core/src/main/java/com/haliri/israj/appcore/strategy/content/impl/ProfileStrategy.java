package com.haliri.israj.appcore.strategy.content.impl;

import com.haliri.israj.appcore.domain.content.Profile;
import com.haliri.israj.appcore.utils.AppUtils;
import com.haliri.israj.appcore.strategy.content.GetDataStrategy;
import com.haliri.israj.appcore.strategy.content.SaveOrUpdateDataStrategy;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by israjhaliri on 8/28/17.
 */
@Repository
@Transactional
public class ProfileStrategy {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private GetDataStrategy getDataStrategy;

    private SaveOrUpdateDataStrategy<Profile> saveOrUpdateDataStrategy;

    public Profile getData() {
        getDataStrategy = (parameter) -> {
            Profile profile = new Profile();
            String sql = "SELECT address, phone, email, lat, lon, create_date, update_date\n" +
                    "FROM content.profile ORDER BY create_date DESC LIMIT 1";

            profile = (Profile) jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper(Profile.class));
            AppUtils.getLogger(this).debug("GET PROFILE LOG : {}", profile.toString());
            return profile;
        };
        return (Profile) getDataStrategy.process(null);
    }

    @Transactional
    public void saveOrUpdate(Profile profile) {
        saveOrUpdateDataStrategy = (Profile parameters) -> {
            String sql;
            if (parameters.getCreateDate() == null || parameters.getCreateDate().equals("")) {
                sql = "INSERT INTO content.profile(\n" +
                        "address, phone, email, lat, lon, create_date, update_date)\n" +
                        "VALUES (?, ?, ?, ?, ?, CURRENT_DATE, CURRENT_DATE)";
                jdbcTemplate.update(sql, parameters.getAddress(), parameters.getPhone(), parameters.getEmail(), parameters.getLat(), parameters.getLon());
            } else {
                sql = "UPDATE content.profile\n" +
                        "SET address=?, phone=?, email=?, lat=?, lon=?, update_date=CURRENT_DATE\n" +
                        "WHERE create_date=?";
                jdbcTemplate.update(sql, parameters.getAddress(), parameters.getPhone(), parameters.getEmail(), parameters.getLat(), parameters.getLon(), parameters.getCreateDate());
            }
        };
        saveOrUpdateDataStrategy.process(profile);
    }
}
