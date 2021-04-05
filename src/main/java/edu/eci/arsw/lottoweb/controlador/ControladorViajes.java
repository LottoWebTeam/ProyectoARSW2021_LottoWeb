package edu.eci.arsw.lottoweb.controlador;

import edu.eci.arsw.lottoweb.modelo.*;
import edu.eci.arsw.lottoweb.servicios.*
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
@RequestMapping("/paseos")
@Api(value = "servicio Paseos")
public class ControladorViajes {
    @Autowired
    private final ServiceLottoWeb serviceLottoWeb;

    @Autowired
    private JwtService jwtService;

    public ViajesController(ServiceLottoWeb serviceLottoWeb) {
        this.serviceLottoWeb = serviceLottoWeb;
    }

    @GetMapping("/")
    @ApiOperation(value = "Obtiene todos los paseos",notes = "devuelve todos los paseos")
    public ResponseEntity<?> getPaseos(){
        try {
            return new ResponseEntity<>(serviceLottoWeb.getPaseos(), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>("No existe registro de paseos", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Encuentra un paseo",notes = "devuelve un paseo por id")
    public ResponseEntity<?> getPaseo(@PathVariable int id){
        try {
            return new ResponseEntity<>(serviceLottoWeb.getPaseo(id), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>("El paseo solicitado no existe", HttpStatus.NOT_FOUND);
        }
    }
}
