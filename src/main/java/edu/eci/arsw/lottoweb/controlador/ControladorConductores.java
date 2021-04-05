package edu.eci.arsw.lottoweb.controlador;

import edu.eci.arsw.lottoweb.modelo.Conductor;
import edu.eci.arsw.lottoweb.modelo.Oferta;
import edu.eci.arsw.lottoweb.servicios.ExceptionServiciosLottoWeb;
import edu.eci.arsw.lottoweb.servicios.ServiceLottoWeb;
import edu.eci.arsw.lottoweb.servicios.impl.JwtService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * ---------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------------------------------------------------------------------------------------------
 * 													CLASE: ControladorConductores
 * ---------------------------------------------------------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------------------------------------------------------
 * @author Santiago Buitrago
 * @author Adriana Catañeda
 * @author Guillermo Castro
 * @version 1.0
 * ---------------------------------------------------------------------------------------------------------------------------
 */

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/conductores")
@Api(value = "servicio Easy Care Conductores")
public class ControladorConductores {


}
