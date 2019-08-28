package com.dany.dany.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Configurable
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService());
//        authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // auth.authenticationProvider(authenticationProvider());
        auth
                .userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/")
                .permitAll()
                .anyRequest()
                .authenticated();

    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/VAADIN/**",
                "/frontend/**",
                "/images/**",
                "/frontend-es5/**", "/frontend-es6/**",
                "/dbconsole/**"
        );
//        web.ignoring().antMatchers(
//                // Vaadin Flow static resources //
//                "/VAADIN/**",
//
//                // the standard favicon URI
//                "/favicon.ico",
//
//                // the robots exclusion standard
//                "/robots.txt",
//
//                // web application manifest //
//                "/manifest.webmanifest",
//                "/sw.js",
//                "/offline-page.html",
//
//                // (development mode) static resources //
//                "/frontend/**",
//
//                // (development mode) webjars //
//                "/webjars/**",
//
//                // (production mode) static resources //
//                "/frontend-es5/**", "/frontend-es6/**");
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .cors()
//                .and()
//                .csrf()
//                .disable()
//                .exceptionHandling()
//                .and()
//               // .sessionManagement()
//               // .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//               // .and()
//                .authorizeRequests()
//                .antMatchers("/",
//                        "/favicon.ico",
//                        "/**/*.png",
//                        "/**/*.gif",
//                        "/**/*.svg",
//                        "/**/*.jpg",
//                        "/**/*.html",
//                        "/**/*.css",
//                        "/**/*.js",
//                        "/**/*.jsp",
//                        "/**/*.do",
//                        "/dbconsole/**")
//                .permitAll();
////        .and()
////        .formLogin()
////        .loginPage("/log")
////        .permitAll();//indicando la ruta que estaremos utilizando.
//
//        //deshabilitando las seguridad contra los frame internos.
//        //Necesario para H2.
//        http.csrf().disable();
//        http.headers().frameOptions().disable();
//
//    }


}
