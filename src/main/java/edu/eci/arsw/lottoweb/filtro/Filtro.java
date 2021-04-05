package edu.eci.arsw.lottoweb.filtro;

import org.springframework.web.filter.OncePerRequestFilter;
<<<<<<< HEAD
=======

>>>>>>> 9d2d5f285bef15a6d7799c074aee012fdf1e2632
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

<<<<<<< HEAD
/**
 * ---------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------------------------------------------------------------------------------------------
 * 													CLASE: Filtro
 * ---------------------------------------------------------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------------------------------------------------------
 * @author Santiago Buitrago
 * @author Adriana Catañeda
 * @author Guillermo Castro
 * @version 1.0
 * ---------------------------------------------------------------------------------------------------------------------------
 */

public class Filtro extends OncePerRequestFilter{
=======
public class Filtro extends OncePerRequestFilter {
>>>>>>> 9d2d5f285bef15a6d7799c074aee012fdf1e2632
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
