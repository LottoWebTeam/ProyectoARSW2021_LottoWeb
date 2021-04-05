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
    private ServiceLottoWeb lottoWebService;

    @Autowired
    private JwtService jwtService;

//    public EasyCareController(EasyCareService easyCareService, JwtService jwtService) {
//        this.easyCareService = easyCareService;
//        this.jwtService = jwtService;
//    }

    @GetMapping("")
    @ApiOperation(value = "Obtener todos los clientes",notes = "retorna todos los clientes")
    public ResponseEntity<?> getClientes(){
        try {
            return new ResponseEntity<>(lottoWebService.getAllClientes(), HttpStatus.ACCEPTED);
        } catch (ExceptionServiciosLottoWeb e) {
            e.printStackTrace();
            return null;
        }
    }


    @GetMapping("/whoami")
    @ApiOperation(value = "Obtiene informacion del cliente autenticado", notes = "El cliente solicitado debe estar autenticado")
    public ResponseEntity<?> getCliente(@RequestHeader("Authorization") String token){
        try{
            String correo = jwtService.user(token);
            if(correo.length() > 0){
                Cliente cliente = this.lottoWebService.getCliente(correo);
                return new ResponseEntity<>(cliente,HttpStatus.OK);
            }else{
                return new ResponseEntity<>("No autenticado", HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
            }
        }catch (Exception e){
            return new ResponseEntity<>("Error en la solicitud", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{documento}/{tdoc}")
    @ApiOperation(value = "Encuentra un cliente",notes = "devuelve un solo cliente por documento y tipo de documento")
    public ResponseEntity<?> getClienteByDocument(@PathVariable String documento, @PathVariable String tdoc){
        try{
            return new ResponseEntity<>(lottoWebService.getCliente(documento,tdoc), HttpStatus.ACCEPTED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("No existe el cliente solicitado", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/cliente/mascotas")
    @ApiOperation(value = "Encuentra las mascotas del cliente logueado", notes = "se debe estar logueado con token valido para obtener estas mascotas")
    public ResponseEntity<?> getMascotas(@RequestHeader("Authorization") String token){
        try{
            String correo = jwtService.user(token);
            if(correo.length() > 0){
                return new ResponseEntity<>(lottoWebService.getVehiculos(correo), HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Error", HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
            }
        }catch (Exception e){
            return new ResponseEntity<>("Error en la solicitud", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/cliente/correo")
    @ApiOperation(value = "Encuentra la información del cliente mediante el correo",notes = "Devuelve el cliente requerido")
    public ResponseEntity<?> getClienteByCorreo(@RequestHeader("Authorization")  String token){
        try{
            String correo = jwtService.user(token);
            if(correo.length() > 0){
                return new ResponseEntity<>(lottoWebService.getCliente(correo), HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<>("Error", HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
            }
        }catch(Exception e){
            return new ResponseEntity<>("Error en la solicitud", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/login/{correo}/{password}")
    public ResponseEntity<?> authenticateUser(@PathVariable String correo, @PathVariable String password) {
        try{
            Cliente cl = lottoWebService.getCliente(correo);
            Conductor ps = lottoWebService.getConductor(correo);
            if(cl!=null && cl.getPassword().equals(password)){
                List<String> roles = new ArrayList<>();
                roles.add("cliente");
                String tk = jwtService.createToken(correo,roles);
                System.out.println(tk);
                return new ResponseEntity<>(tk, HttpStatus.OK);
            }
            else if(ps != null && ps.getPassword().equals(password)){
                List<String> roles = new ArrayList<>();
                roles.add("paseador");
                return new ResponseEntity<>(jwtService.createToken(correo,roles), HttpStatus.ACCEPTED);
            }
            else{
                return new ResponseEntity<>("Rechazo", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (ExceptionServiciosLottoWeb e){
            return new ResponseEntity<>("Rechazo", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login/validate")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String token){
        System.out.println(token + " Este es el token que llega");
        if(jwtService.user(token).length() >= 0) return new ResponseEntity<>("ok", HttpStatus.ACCEPTED);
        return new ResponseEntity<>("mal", HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
    }

    @PostMapping("/register/{correo}/{password}/{nombre}/{cedula}/{telefono}")
    public ResponseEntity<?> registrar(@PathVariable String correo, @PathVariable String password, @PathVariable String nombre, @PathVariable String cedula, @PathVariable String telefono){
        try{
            System.out.println("yaaaaaaaaaaaaaaaaaaaaaaaaa");
            Cliente cliente = new Cliente();
            cliente.setCorreo(correo);
            cliente.setPassword(password);
            cliente.setNombre(nombre);
            cliente.setTipoDocumento("cedula");
            cliente.setDocumento(cedula);
            cliente.setTelefono(telefono);
            lottoWebService.saveCliente(cliente);
            List<String> roles = new ArrayList<>();
            roles.add("cliente");
            String tok = jwtService.createToken(cliente.getCorreo(), roles);
            return new ResponseEntity<>(tok, HttpStatus.ACCEPTED);
        }catch (ExceptionServiciosLottoWeb e){
            e.printStackTrace();
            return new ResponseEntity<>("Rechazo", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}