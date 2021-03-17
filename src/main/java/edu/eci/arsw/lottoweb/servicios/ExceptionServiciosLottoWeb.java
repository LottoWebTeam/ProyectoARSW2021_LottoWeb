package edu.eci.arsw.lottoweb.servicios;

import edu.eci.arsw.lottoweb.persistencia.*;

/**
 * ---------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------------------------------------------------------------------------------------------
 * 													CLASE: ExceptionServiciosLottoWeb
 * ---------------------------------------------------------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------------------------------------------------------
 * @author Santiago Buitrago
 * @author Adriana Catañeda
 * @author Guillermo Castro
 * @version 1.0
 * ---------------------------------------------------------------------------------------------------------------------------
 */

public class ExceptionServiciosLottoWeb extends Exception{
    public ExceptionServiciosLottoWeb(String message, PersistenceException e) { }
}
