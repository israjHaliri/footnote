package me.haliri.israj.appcore.repository.impl;

import me.haliri.israj.appcore.constant.Response;
import me.haliri.israj.appcore.domain.ResultMessage;
import me.haliri.israj.appcore.domain.Role;
import me.haliri.israj.appcore.domain.User;
import me.haliri.israj.appcore.repository.BaseRepository;
import me.haliri.israj.appcore.repository.UserRespository;
import me.haliri.israj.appcore.utils.AppUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.transform.Result;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by israjhaliri on 8/28/17.
 */
@Repository
@Transactional
public class UserRepositoryImpl implements UserRespository {

    @Autowired
    private DataSource dataSource;

    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User getData() {
        return null;
    }

    @Override
    public List<User> getListData() {
        return null;
    }

    @Override
    public List<User> getListDataByParameters(Object parameters) {
        return null;
    }

    @Override
    public User getDataById(Object id) {
        try {

            String sql = "SELECT * FROM CONFIG.USERS WHERE ID= ?";
            User user = (User) jdbcTemplate.queryForObject(sql, new Object[]{id.toString()}, new BeanPropertyRowMapper(User.class));

            String sqlRole = "SELECT ur.user_id,r.role,r.id FROM CONFIG.user_roles ur JOIN CONFIG.role r on ur.role_id = r.id WHERE USER_ID = ?";
            List<Role> roles = jdbcTemplate.query(sqlRole, new Object[]{id.toString()}, new BeanPropertyRowMapper(Role.class));

            user.setRoles(roles);
            AppUtils.getLogger(this).debug("USERS LOG : {}", user.toString());
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return new User();
        }
    }

    @Override
    public User getDataByParameters(Object parameters) {
        return null;
    }

    @Override
    public void saveData(User user) {
        String sql = "insert into config.users (id,password,enable) values (?,?,?)";
        jdbcTemplate.update(sql, new Object[]{user.getId(), user.getPassword(), user.getEnable()});
        String sqlRole = "insert into config.role (role,user_id) values (?,?,?)";
        for (Role role : user.getRoles()) {
            jdbcTemplate.update(sqlRole, new Object[]{role.getRole(), role.getUserId()});
        }
    }

    @Override
    public void updateData(User parameters) {}

    @Override
    public void saveOrUpdate(User parameters) {}

    @Override
    public void deleteData(Object id) {}
}
