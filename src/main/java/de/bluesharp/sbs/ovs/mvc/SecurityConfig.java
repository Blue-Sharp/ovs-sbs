/*
 * Copyright 2016-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.bluesharp.sbs.ovs.mvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.sql.DataSource;

import static de.bluesharp.sbs.ovs.Application.Profile.LOCAL;
import static de.bluesharp.sbs.ovs.Application.Profile.NOT_LOCAL;

@Configuration
@Slf4j
public class SecurityConfig {

    @Configuration
    @Profile(NOT_LOCAL)
    @EnableWebSecurity
    static class DefaultWebSecurityConfigurer extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) {
            try {
                //@formatter:off
                http.csrf().disable()
                            .authorizeRequests()
                            .antMatchers("/").permitAll()
                            .antMatchers("/javax.faces.resource/**").permitAll()
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
        public void configureGlobal(AuthenticationManagerBuilder auth, DataSource dataSource) throws Exception {
            auth.jdbcAuthentication()
                    .dataSource(dataSource)
                    .withDefaultSchema();
        }
    }

    @Configuration
    @Profile(LOCAL)
    @EnableWebSecurity
    static class LocalWebSecurityConfigurer extends WebSecurityConfigurerAdapter {

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
        public void configureGlobal(AuthenticationManagerBuilder auth, DataSource dataSource) throws Exception {
            auth.jdbcAuthentication()
                    .dataSource(dataSource)
                    .withDefaultSchema()
                    .withUser(User.withDefaultPasswordEncoder()
                            .username("pirat")
                            .password("123")
                            .authorities("ADMIN", "CHAIRMAN", "MANAGER", "STUDENT", "PARTNER")
                            .build());
        }
    }
}
