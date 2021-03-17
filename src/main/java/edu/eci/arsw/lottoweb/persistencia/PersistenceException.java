package edu.eci.arsw.lottoweb.persistencia;

/**
 * ---------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------------------------------------------------------------------------------------------
 * 													CLASE: ControladorLottoWeb
 * ---------------------------------------------------------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------------------------------------------------------
 * @author Santiago Buitrago
 * @author Adriana Catañeda
 * @author Guillermo Castro
 * @version 1.0
 * ---------------------------------------------------------------------------------------------------------------------------
 */

public class PersistenceException extends  Exception{

        public static final String NO_ENCONTRADO = "NO ENCONTRADO";
        public static final String NO_AGREGADO = "NO FUE POSIBLE AGREGAR";
        public static final String ERROR_EN_LA_SOLICITUD = "ERROR EN LA SOLICITUD";

        public PersistenceException(String string){ }
}
