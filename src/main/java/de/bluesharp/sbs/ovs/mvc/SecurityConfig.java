package de.bluesharp.sbs.ovs.mvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

import static de.bluesharp.sbs.ovs.Application.Profile.LOCAL;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) {
        try {
            //@formatter:off
                http.csrf().disable()
                            .headers().frameOptions().disable()
                        .and()
                            .authorizeRequests()
                            .antMatchers("/").permitAll()
                            .antMatchers("/javax.faces.resource/**").permitAll()
                            .antMatchers("/h2-console/**").permitAll()
                            .anyRequest().authenticated()
                        .and()
                            .formLogin()
                            .loginPage("/login.jsf")
                            .permitAll()
                            .failureUrl("/login.jsf?error=true")
                            .defaultSuccessUrl("/index.jsf")
                        .and()
                            .logout()
                            .logoutUrl("/logout.jsf")
                            .logoutSuccessUrl("/login.jsf")
                            .invalidateHttpSession(true)
                            .deleteCookies("JSESSIONID");
                //@formatter:on
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, DataSource dataSource, Environment environment) throws Exception {
        boolean isLocal = environment.acceptsProfiles(LOCAL);
        //boolean isLocal = Arrays.stream(environment.getActiveProfiles()).anyMatch((i) -> i.equals(LOCAL));
        if (isLocal) {
            PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            String password = encoder.encode("123");

            UserDetails me = User.builder()
                    .username("pirat")
                    .password(password)
                    .authorities("ADMIN", "CHAIRMAN", "MANAGER", "STUDENT", "PARTNER")
                    .build();

            auth.jdbcAuthentication()
                    .dataSource(dataSource)
                    .withDefaultSchema()
                    .withUser(me);
        } else {
            auth.jdbcAuthentication()
                    .dataSource(dataSource)
                    .withDefaultSchema();
        }
    }
}
