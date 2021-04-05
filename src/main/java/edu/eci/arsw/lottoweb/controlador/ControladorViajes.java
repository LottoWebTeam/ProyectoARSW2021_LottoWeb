package edu.eci.arsw.lottoweb.controlador;

import edu.eci.arsw.lottoweb.modelo.*;
import edu.eci.arsw.lottoweb.servicios.*;
import edu.eci.arsw.lottoweb.servicios.impl.JwtService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * ---------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------------------------------------------------------------------------------------------
 * 													CLASE: ControladorViajes
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
@RequestMapping("/viajes")
@Api(value = "servicio Viajes")
public class ControladorViajes {
    @Autowired
    private final ServiceLottoWeb serviceLottoWeb;

    @Autowired
    private JwtService jwtService;

    public ControladorViajes(ServiceLottoWeb serviceLottoWeb) {
        this.serviceLottoWeb = serviceLottoWeb;
    }

    @GetMapping("/")
    @ApiOperation(value = "Obtiene todos los viajes",notes = "devuelve todos los viajes")
    public ResponseEntity<?> getViajes(){
        try {
            return new ResponseEntity<>(serviceLottoWeb.getViajes(), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>("No existe registro de viajes", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Encuentra un viaje",notes = "devuelve un viaje por id")
    public ResponseEntity<?> getViaje(@PathVariable int id){
        try {
            return new ResponseEntity<>(serviceLottoWeb.getViaje(id), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>("El viaje solicitado no existe", HttpStatus.NOT_FOUND);
        }
    }
}
