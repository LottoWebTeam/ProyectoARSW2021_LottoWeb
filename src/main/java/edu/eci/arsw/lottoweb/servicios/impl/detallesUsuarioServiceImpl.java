package edu.eci.arsw.lottoweb.servicios.impl;

import edu.eci.arsw.lottoweb.modelo.*;
import edu.eci.arsw.lottoweb.servicios.*;
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

/**
 * ---------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------------------------------------------------------------------------------------------
 * 													CLASE: detallesUsuarioServiceImpl
 * ---------------------------------------------------------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------------------------------------------------------
 * @author Santiago Buitrago
 * @author Adriana Catañeda
 * @author Guillermo Castro
 * @version 1.0
 * ---------------------------------------------------------------------------------------------------------------------------
 */

@Service
@Primary
public class detallesUsuarioServiceImpl implements UserDetailsService {

    @Autowired
    private final ServiceLottoWeb serviceLottoWeb;

    public UserDetailsServiceImpl(ServiceLottoWeb serviceLottoWeb) {
        this.serviceLottoWeb = serviceLottoWeb;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQQ");
            Cliente cl = serviceLottoWeb.getCliente(username);
            Conductor ps = serviceLottoWeb.getConductor(username);
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
