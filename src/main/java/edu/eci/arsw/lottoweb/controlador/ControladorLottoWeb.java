package edu.eci.arsw.lottoweb.controlador;

import edu.eci.arsw.lottoweb.modelo.*;
import edu.eci.arsw.lottoweb.servicios.*;
import edu.eci.arsw.lottoweb.servicios.impl.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

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

@org.springframework.web.bind.annotation.RestController
@Controller
@CrossOrigin("*")
@RequestMapping({"/api", "/clients"})
public class ControladorLottoWeb {

    @Autowired
    private ServiceLottoWeb serviceLottoWeb;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login/{correo}/{password}")
    public ResponseEntity<?> authenticateUser(@PathVariable String correo, @PathVariable String password) {
        try{
            Cliente cliente = ServiceLottoWeb.getCliente(correo);
            Conductor conductor= ServiceLottoWeb.getConductor(correo);
            if((cliente!=null) && (cliente.getPassword().equals(password))){
                List<String> roles = new ArrayList<>();
                roles.add("cliente");
                String tk = jwtService.createToken(correo,roles);
                System.out.println(tk);
                return new ResponseEntity<>(tk, HttpStatus.OK);
            }
            else if((conductor!=null) && (conductor.getPassword().equals(password))){
                List<String> roles = new ArrayList<>();
                roles.add("cliente");
                String tk = jwtService.createToken(correo,roles);
                System.out.println(tk);
                return new ResponseEntity<>(tk, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("RECHAZADO", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (ExceptionServiciosLottoWeb e){
            return new ResponseEntity<>("RECHAZADO", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login/validate")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String token){
        System.out.println(token + " Este es el token que llega");
        if(jwtService.user(token).length() >= 0) return new ResponseEntity<>("ok", HttpStatus.ACCEPTED);
        return new ResponseEntity<>("mal", HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
    }

    @PostMapping("/register/{correo}/{password}/{nombre}/{cedula}/{telefono}")
    public ResponseEntity<?> restrar(@PathVariable int documento,@PathVariable String tipoDocumento,@PathVariable String nombre,@PathVariable String correo,@PathVariable int telefono,@PathVariable String password){
        try{
            Cliente cliente = new Cliente( documento, tipoDocumento,nombre,correo,telefono,password);
            ServiceLottoWeb.guardarCliente(cliente);
            List<String> roles = new ArrayList<>();
            roles.add("cliente");
            String tok = jwtService.createToken(cliente.getCorreo(), roles);
            return new ResponseEntity<>(tok, HttpStatus.ACCEPTED);
        }catch (ExceptionServiciosLottoWeb e){
            e.printStackTrace();
            return new ResponseEntity<>("RECHAZO", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}