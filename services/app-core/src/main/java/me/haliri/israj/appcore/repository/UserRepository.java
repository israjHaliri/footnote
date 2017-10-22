package me.haliri.israj.appcore.repository;

import me.haliri.israj.appcore.config.MainDatasourceConfig;
import me.haliri.israj.appcore.domain.Role;
import me.haliri.israj.appcore.domain.User;
import me.haliri.israj.appcore.utils.AppUtils;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class UserRepository {

    @Autowired
    private MainDatasourceConfig dataSource;

    public User findByUsername(String username){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource.dataSource());

        String sql = "SELECT * FROM USERS WHERE ID= ?";

        List<Map<String,Object>> userMap = jdbcTemplate.queryForList(sql, new Object[] { username });
        AppUtils.getLogger(this).debug("USERS LOG : {}",userMap.toString());

        if(userMap.size() > 0){
            User user = new User();
            Role role = new Role();
            for (Map param : userMap){
                user.setId(param.get("id").toString());
                user.setPassword(param.get("password").toString());
                Integer enabled = (Integer) param.get("enabled");
                if(enabled == 1){
                    user.setEnabled(true);
                }else{
                    user.setEnabled(false);
                }


                role.setId(param.get("id").toString());
                role.setRole(param.get("role").toString());

                Set<Role> roleSet =  new HashSet<>();
                roleSet.add(role);
                user.setRoles(roleSet);
            }
            return user;
        }
        return new User();

    }
}
