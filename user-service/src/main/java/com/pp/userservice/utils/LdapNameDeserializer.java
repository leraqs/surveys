package com.pp.userservice.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.ldap.InvalidNameException;

import javax.naming.ldap.LdapName;
import java.io.IOException;

public class LdapNameDeserializer extends JsonDeserializer<LdapName> {
    @Override
    public LdapName deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        try {
            return new LdapName(p.getValueAsString());
        } catch (InvalidNameException | javax.naming.InvalidNameException e) {
            throw new IOException(e);
        }
    }
}
