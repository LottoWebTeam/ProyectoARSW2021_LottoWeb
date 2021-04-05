package edu.eci.arsw.lottoweb.servicios.impl;

import edu.eci.arsw.easycare.model.Cliente;
import edu.eci.arsw.easycare.model.Paseador;
import edu.eci.arsw.easycare.service.EasyCareService;
import edu.eci.arsw.easycare.service.ExceptionServiciosEasyCare;
import edu.eci.arsw.lottoweb.modelo.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private final EasyCareService easyCareService;

    public UserDetailsServiceImpl(EasyCareService easyCareService) {
        this.easyCareService = easyCareService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQQ");
            Cliente cl = easyCareService.getCliente(username);
            Paseador ps = easyCareService.getPaseador(username);
            if(cl != null){
                System.out.println(userBuilder(cl.getCorreo(), cl.getPassword(), new BCryptPasswordEncoder().encode(cl.getPassword()),"cliente").getPassword() +" &&&&&&&&&&&&&&&&&&&&&&&&&&&&");
                return userBuilder(cl.getCorreo(), cl.getPassword(), new BCryptPasswordEncoder().encode(cl.getPassword()),"cliente");
            }
            else if(ps != null){
                return userBuilder(ps.getCorreo(), ps.getPassword(), new BCryptPasswordEncoder().encode(ps.getPassword()),"paseador");
            }
            throw new UsernameNotFoundException("Usuario no valido");
        }catch (ExceptionServiciosEasyCare e){
            System.out.println("malllllllllllllllll");
            throw new UsernameNotFoundException("Usuario no valido");
        }

    }

    private User userBuilder(String correo, String password, String... roles){
        boolean enabled = true;
        boolean accountNotExpired = true;
        boolean credentialsNotExpired = true;
        boolean accountNotLocked = true;
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(String role: roles){
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role));
        }
        return new User(correo,password,enabled,accountNotExpired,credentialsNotExpired,accountNotLocked, authorities);
    }
}
