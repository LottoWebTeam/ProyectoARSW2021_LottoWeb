package edu.eci.arsw.lottoweb.servicios;

import edu.eci.arsw.lottoweb.modelo.*;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ---------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------------------------------------------------------------------------------------------
 * 													CLASE: ServiceLottoWeb
 * ---------------------------------------------------------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------------------------------------------------------
 * @author Santiago Buitrago
 * @author Adriana Catañeda
 * @author Guillermo Castro
 * @version 1.0
 * ---------------------------------------------------------------------------------------------------------------------------
 */

public interface ServiceLottoWeb {

    static Cliente getCliente(String correo) throws ExceptionServiciosLottoWeb {
        return null;
    }

    static Conductor getConductor(String correo) throws ExceptionServiciosLottoWeb {
        return null;
    }

    static void guardarCliente(Cliente cliente) throws ExceptionServiciosLottoWeb {

    }

    static void guardarConductor(Conductor conductor) throws ExceptionServiciosLottoWeb {

    }

    static void solicitarServicio(Cliente cliente, Oferta oferta) throws ExceptionServiciosLottoWeb {

    }
}
