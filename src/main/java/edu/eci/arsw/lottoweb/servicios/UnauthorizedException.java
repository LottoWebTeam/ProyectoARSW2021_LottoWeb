package edu.eci.arsw.lottoweb.servicios;

/**
 * ---------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------------------------------------------------------------------------------------------
 * 													CLASE: UnauthorizedException
 * ---------------------------------------------------------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------------------------------------------------------
 * @author Santiago Buitrago
 * @author Adriana Catañeda
 * @author Guillermo Castro
 * @version 1.0
 * ---------------------------------------------------------------------------------------------------------------------------
 */

public class UnauthorizedException extends RuntimeException {

    private static final String DESCRIPTION = "Unauthorized Exception (401)";

    public UnauthorizedException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}

