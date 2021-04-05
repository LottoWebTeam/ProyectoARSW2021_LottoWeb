package edu.eci.arsw.lottoweb.servicios.impl;

package edu.eci.arsw.easycare.service.impl;

import edu.eci.arsw.easycare.model.Cliente;
import edu.eci.arsw.easycare.model.Paseador;
import edu.eci.arsw.easycare.service.EasyCareService;
import edu.eci.arsw.easycare.service.ExceptionServiciosEasyCare;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JwtAuthorizacionFiltro extends BasicAuthenticationFilter {

    private static final String AUTHORIZATION = "Authorization";

    @Autowired
    private JwtService jwtService;


    public JwtAuthorizationFilter(AuthenticationManager authenticationManager){
        super(authenticationManager);
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        String authHeader = request.getHeader(AUTHORIZATION);
        if (jwtService.isBearer(authHeader)) {
            LogManager.getLogger(this.getClass().getName()).debug(">>> FILTER JWT...");
            List<GrantedAuthority> authorities = jwtService.roles(authHeader).stream()
                    .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(jwtService.user(authHeader), null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
