package com.mommyce.appcore.strategy.barber.impl;

import com.mommyce.appcore.domain.barber.BarberProfile;
import com.mommyce.appcore.strategy.barber.DeleteDataStrategy;
import com.mommyce.appcore.strategy.barber.GetDataStrategy;
import com.mommyce.appcore.strategy.barber.SaveOrUpdateDataStrategy;
import com.mommyce.appcore.utils.AppUtils;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by israjhaliri on 8/28/17.
 */
@Repository
@Transactional
public class BarberProfileStrategyImpl {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private GetDataStrategy getDataStrategy;

    private SaveOrUpdateDataStrategy<BarberProfile> saveOrUpdateDataStrategy;

    public BarberProfile getData() {
        getDataStrategy = (parameter) -> {
            BarberProfile barberProfile = new BarberProfile();
            String sql = "SELECT address, phone, email, lat, lon, create_date, update_date\n" +
                    "FROM barber.profile ORDER BY create_date DESC LIMIT 1";
            try {
                barberProfile = (BarberProfile) jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper(BarberProfile.class));
            } catch (Exception e) {
                AppUtils.getLogger(this).error("ERROR PROFILE LOG GET DATA: {}", e.getMessage());
            }

            AppUtils.getLogger(this).debug("GET PROFILE LOG : {}", barberProfile.toString());
            return barberProfile;
        };
        return (BarberProfile) getDataStrategy.process(null);
    }

    @Transactional
    public void saveOrUpdate(BarberProfile barberProfile) {
        saveOrUpdateDataStrategy = (BarberProfile parameters) -> {
            BarberProfile dataProfile = getData();
            String sql;

            if (dataProfile.getCreateDate() == null || dataProfile.getCreateDate().equals("")) {
                sql = "INSERT INTO barber.profile(\n" +
                        "address, phone, email, lat, lon, create_date, update_date)\n" +
                        "VALUES (?, ?, ?, ?, ?, current_timestamp, current_timestamp)";
                jdbcTemplate.update(sql, parameters.getAddress(), parameters.getPhone(), parameters.getEmail(), parameters.getLat(), parameters.getLon());
            } else {
                sql = "UPDATE barber.profile\n" +
                        "SET address=?, phone=?, email=?, lat=?, lon=?, create_date=current_timestamp, update_date=current_timestamp\n" +
                        "WHERE create_date=?";
                jdbcTemplate.update(sql, parameters.getAddress(), parameters.getPhone(), parameters.getEmail(), parameters.getLat(), parameters.getLon(), dataProfile.getCreateDate());
            }
        };
        saveOrUpdateDataStrategy.process(barberProfile);
    }
}
