package org.itstep.msk.app.config;

import org.itstep.msk.app.service.PlainPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private PlainPasswordEncoder passwordEncoder;

    @Override
    public void configure(WebSecurity webSecurity) {
        webSecurity.ignoring()
                .antMatchers("/css/**")
                .antMatchers("/js/**")
                .antMatchers("/images/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authentication) throws Exception {
        String userQuery = "SELECT username, password, 1 AS active FROM users WHERE username is ?";
        String roleQuery = "SELECT u.username, r.role "
                + "FROM users u "
                + "INNER JOIN users_roles ur ON ue.user_id = u.id "
                + "INNER JOIN roles r ON r.id = "
                + "ur.role_id "
                + "WHERE username = ?";

        authentication.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(userQuery)
                .authoritiesByUsernameQuery(roleQuery)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/menu").permitAll()
                .antMatchers("/denied").permitAll()
                .anyRequest().authenticated();

        httpSecurity.csrf().disable();

        httpSecurity.formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/menu")
                .failureUrl("/denied")
                .usernameParameter("username")
                .passwordParameter("password");

        httpSecurity.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/logout");

        httpSecurity.exceptionHandling()
                .accessDeniedPage("/denied");
    }
}
