package com.pp.userservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.ldap.search.FilterBasedLdapUserSearch;
import org.springframework.security.ldap.userdetails.LdapUserDetailsService;
import reactor.core.publisher.Mono;

@Configuration
public class LdapConfig {

    @Bean
    public ReactiveAuthenticationManager authenticationManager(ReactiveUserDetailsService userDetailsService) {
        return new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService);
    }

    @Bean
    public ReactiveUserDetailsService userDetailsService(LdapUserDetailsService ldapUserDetailsService) {
        return username -> Mono.fromCallable(() -> ldapUserDetailsService.loadUserByUsername(username));
    }

    @Bean
    public LdapUserDetailsService ldapUserDetailsService() {
        String searchBase = "ou=people";
        String searchFilter = "(uid={0})";

        LdapContextSource contextSource = new LdapContextSource();
        contextSource.setUrl("ldap://localhost:8389");
        contextSource.setBase("dc=springframework,dc=org");
        contextSource.afterPropertiesSet();

        return new LdapUserDetailsService(new FilterBasedLdapUserSearch(searchBase, searchFilter, contextSource));
    }

}
