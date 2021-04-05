package edu.eci.arsw.lottoweb.configuracion;

import edu.eci.arsw.lottoweb.filtro.Filtro;
import edu.eci.arsw.lottoweb.servicios.impl.JwtAuthorizacionFiltro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class Security extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http
                .csrf().disable()
                .httpBasic()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests().antMatchers("/**").permitAll().anyRequest().authenticated();
        http.addFilter(jwtAuthorizacionFiltro());
    }

    @Bean
    public FilterRegistrationBean<Filtro> myFilter(){
        FilterRegistrationBean<Filtro> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new Filtro());
        filterRegistrationBean.addUrlPatterns();
        return filterRegistrationBean;
    }

    @Bean
    public JwtAuthorizacionFiltro jwtAuthorizacionFiltro() throws Exception {
        return new JwtAuthorizacionFiltro(this.authenticationManager());
    }

}
