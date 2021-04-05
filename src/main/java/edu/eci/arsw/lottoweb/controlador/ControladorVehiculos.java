package edu.eci.arsw.lottoweb.controlador;

import edu.eci.arsw.lottoweb.servicios.ServiceLottoWeb;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * ---------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------------------------------------------------------------------------------------------
 * 													CLASE: ControladorVehiculos
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
@RequestMapping("/vehiculos")
@Api(value = "servicio Vehiculos")
public class ControladorVehiculos {
    @Autowired
    private final ServiceLottoWeb lottowebService;

    public ControladorVehiculos(ServiceLottoWeb lottowebService) {
        this.lottowebService = lottowebService;
    }

    @GetMapping("")
    @ApiOperation(value = "Obtiene todas las vehiculos",notes = "devuelve todas las vehiculos")
    public ResponseEntity<?> getVehiculos(){
        try {
            return new ResponseEntity<>(lottowebService.getVehiculos(), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>("No se encontraron vehiculos", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "encuentra una vehiculo",notes = "devuelve una vehiculo por id")
    public ResponseEntity<?> getVehiculo(@PathVariable int id){
        try {
            return new ResponseEntity<>(lottowebService.getVehiculo(id),HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>("No se encontró la vehiculo requerida",HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    @ApiOperation(value = "Registra vehiculo", notes = "No retorna algún valor")
    public ResponseEntity<?> postVehiculo(@RequestBody Vehiculo vehiculo){
        try{
            System.out.println("entre");
            lottowebService.saveVehiculo(vehiculo);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("No fue posible registrar la vehiculo", HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("")
    @ApiOperation(value = "Permite actualizar la vehiculo", notes = "No devuelve algún valor")
    public ResponseEntity<?> actualizarVehiculo(@Valid @RequestBody Vehiculo vehiculo){
        try {
            System.out.println(vehiculo.getNombre());
            lottowebService.updateVehiculo(vehiculo);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("No fue posible actualizar la vehiculo", HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/vehiculo/{id}")
    @ApiOperation(value = "Permite eliminar la vehiculo deseada de la base de datos", notes = "No devueve algún valor")
    public ResponseEntity<?> deletevehiculo(@PathVariable int id){
        try {
            System.out.println(id);
            lottowebService.deleteVehiculo(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("No fue posible eliminar la vehiculo", HttpStatus.FORBIDDEN);
        }
    }

}