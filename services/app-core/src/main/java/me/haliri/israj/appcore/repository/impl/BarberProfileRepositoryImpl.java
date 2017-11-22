package me.haliri.israj.appcore.repository.impl;

import me.haliri.israj.appcore.domain.BarberProfile;
import me.haliri.israj.appcore.domain.Role;
import me.haliri.israj.appcore.domain.User;
import me.haliri.israj.appcore.repository.BarberProfileRespository;
import me.haliri.israj.appcore.repository.BaseRepository;
import me.haliri.israj.appcore.utils.AppUtils;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jmx.support.ObjectNameManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by israjhaliri on 8/28/17.
 */
@Repository
@Transactional
public class BarberProfileRepositoryImpl implements BarberProfileRespository {

    @Autowired
    private DataSource dataSource;

    private final JdbcTemplate jdbcTemplate;

    public BarberProfileRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public BarberProfile getData() {
        BarberProfile barberProfile = new BarberProfile();
        try {
            String sql = "SELECT address, phone, email, lat, lon, create_date, update_date\n" +
                    "FROM barber.profile ORDER BY create_date DESC LIMIT 1";

            barberProfile = (BarberProfile) jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper(BarberProfile.class));

        } catch (Exception e) {
            e.printStackTrace();
        }

        AppUtils.getLogger(this).debug("GET PROFILE LOG : {}", barberProfile.toString());
        return barberProfile;
    }

    @Override
    public BarberProfile getDataById(Object id) {
        return null;
    }


    @Override
    @Transactional
    public void saveOrUpdate(BarberProfile barberProfile) {
        BarberProfile dataProfile = getData();
        String sql;
        if (dataProfile.getCreateDate() == null || dataProfile.getCreateDate().equals("")) {
            sql = "INSERT INTO barber.profile(\n" +
                    "address, phone, email, lat, lon, create_date, update_date)\n" +
                    "VALUES (?, ?, ?, ?, ?, current_timestamp, current_timestamp)";
            jdbcTemplate.update(sql, barberProfile.getAddress(), barberProfile.getPhone(), barberProfile.getEmail(), barberProfile.getLat(), barberProfile.getLon());
        } else {
            sql = "UPDATE barber.profile\n" +
                    "SET address=?, phone=?, email=?, lat=?, lon=?, create_date=current_timestamp, update_date=current_timestamp\n" +
                    "WHERE create_date=?";
            jdbcTemplate.update(sql, barberProfile.getAddress(), barberProfile.getPhone(), barberProfile.getEmail(), barberProfile.getLat(), barberProfile.getLon(), dataProfile.getCreateDate());
        }
    }

    @Override
    public BarberProfile getDataByParameters(Object parameters) {
        return null;
    }

    @Override
    public void saveData(BarberProfile parameters) {
    }

    @Override
    public void updateData(BarberProfile parameters) {
    }

    @Override
    public void deleteData(Object id) {
    }
}
