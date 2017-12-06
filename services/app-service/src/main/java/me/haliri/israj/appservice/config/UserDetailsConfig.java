package me.haliri.israj.appservice.config;


import me.haliri.israj.appcore.domain.common.Role;
import me.haliri.israj.appcore.domain.common.User;
import me.haliri.israj.appcore.dao.common.impl.UserDaoImpl;
import me.haliri.israj.appcore.utils.AppUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by israjhaliri on 8/25/17.
 */

@Service
public class UserDetailsConfig implements UserDetailsService {

    @Autowired
    UserDaoImpl userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  userRepository.getDataById(username);
        AppUtils.getLogger(this).debug("USERNAME PARAMETER : {}, DETAIL : {}",username, user.toString());
        if (user.getId() == null) {
            throw new UsernameNotFoundException(username);
        } else {
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            user.getRoles().forEach((users)->{
                grantedAuthorities.add(new SimpleGrantedAuthority(users.getRole()));
            });
            return new org.springframework.security.core.userdetails.User(user.getId(), user.getPassword(),user.getEnable(), true, true, true, grantedAuthorities);
        }
    }
}