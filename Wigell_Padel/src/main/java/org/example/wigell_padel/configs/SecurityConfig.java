package org.example.wigell_padel.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
//@EnableMethodSecurity
public class SecurityConfig{
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf)-> csrf.disable())
                .authorizeHttpRequests((auth)-> auth
                        .requestMatchers("/api/v5/availability").permitAll()
                        .requestMatchers("/api/v5/customers").hasRole("ADMIN")
                        .requestMatchers("/api/v5/addfield").hasRole("ADMIN")
                        .requestMatchers("/api/v5/deletefield/{id}").hasRole("ADMIN")
                        .requestMatchers("/api/v5/updateinfo").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .httpBasic();
                /*.httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())*/


                /*.sessionManagement((sm) -> sm
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));*/

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user")
                .roles("USER")
                .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);

    }
}
