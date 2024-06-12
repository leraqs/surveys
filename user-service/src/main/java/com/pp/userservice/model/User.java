package com.pp.userservice.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pp.userservice.utils.LdapNameDeserializer;
import com.pp.userservice.utils.LdapNameSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.ldap.LdapName;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entry(base = "ou=people", objectClasses = {"top", "account", "extensibleObject"})
public class User {

    @Id
    @JsonSerialize(using = LdapNameSerializer.class)
    @JsonDeserialize(using = LdapNameDeserializer.class)
    private LdapName dn;

    @Attribute(name = "uid")
    private String username;

    @Attribute(name = "userPassword")
    private String password;

    @Attribute(name = "mail")
    private String email;

}

