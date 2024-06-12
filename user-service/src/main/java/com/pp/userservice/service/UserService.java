package com.pp.userservice.service;

import com.pp.userservice.model.CreateUserRequest;
import com.pp.userservice.model.User;
import com.pp.userservice.model.UserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.ldap.LdapName;

@Service
@RequiredArgsConstructor
public class UserService {

    private final LdapTemplate ldapTemplate;

    private final PasswordEncoder passwordEncoder;

    public User registerUser(CreateUserRequest req) {
        String encodedPassword = passwordEncoder.encode(req.password());

        LdapName dn = LdapNameBuilder.newInstance()
                .add("dc", "org")
                .add("dc", "springframework")
                .add("ou", "people")
                .add("uid", req.username())
                .build();

        User user = User.builder()
                .dn(dn)
                .username(req.username())
                .email(req.email())
                .password("{bcrypt}" + encodedPassword)
                .build();

        ldapTemplate.create(user);

        return user;
    }

    public UserDetails getUserDetails(String uid) {
        User user = ldapTemplate.findOne(
                LdapQueryBuilder.query().where("uid").is(uid),
                User.class
        );
        return new UserDetails(user);
    }
}

