/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.tentspots.security;

import com.tsguild.tentspots.data.LoginRepository;
import com.tsguild.tentspots.model.Login;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author pspethmann
 */


@Component
public class UserService implements UserDetailsService {

    private static boolean hasSeeded = false;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    LoginRepository repo;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        // Little hack to guarantee we have data in our database.
        // Not suitable for production code!
        if (!hasSeeded) {
            seed();
            hasSeeded = true;
        }

        Login login = repo.findById(userName).orElse(null);
        if (login == null) {
            return null;
        }

        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(login.getRole()));

        User user = new User(login.getUserName(), login.getPasswordHash(), roles);

        return user;
    }

    private void seed() {
        seedUser("user", "password", "SALES");
        seedUser("admin", "admin", "SUPER_ADMIN");
    }

    private void seedUser(String userName, String password, String role) {
        Login login = repo.findById(userName).orElse(null);
        if (login == null) {
            login = new Login();
            login.setUserName(userName);
            login.setPasswordHash(encoder.encode(password));
            login.setRole(role);
            repo.save(login);
        }

    }

}

