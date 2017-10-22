package me.haliri.israj.appservice.config;


import me.haliri.israj.appcore.domain.Role;
import me.haliri.israj.appcore.domain.User;
import me.haliri.israj.appcore.repository.UserRepository;
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
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  userRepository.findByUsername(username);
        AppUtils.getLogger(this).info("USERNAME PARAMETER : {}, DETAIL : {}",username, user.toString());
        if (user.getId() == null) {
            throw new UsernameNotFoundException(username);
        } else {

            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            for (Role roles : user.getRoles()) {
                grantedAuthorities.add(new SimpleGrantedAuthority(roles.getRole()));
            }

            return new org.springframework.security.core.userdetails.User(user.getId(), user.getPassword(),user.getEnabled(), true, true, true, grantedAuthorities);
        }
    }
}