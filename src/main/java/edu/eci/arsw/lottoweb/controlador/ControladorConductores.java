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
@Api(value = "servicio LottoWeb Conductores")
public class ControladorConductores {

    @Autowired
    private final ServiceLottoWeb lottowebService;

    @Autowired
    private final JwtService jwtService;

    public ControladorConductores(ServiceLottoWeb lottowebService, JwtService jwtService) {
        this.lottowebService = lottowebService;
        this.jwtService = jwtService;
    }


    @GetMapping("")
    @ApiOperation(value = "Obtiene todos los conductores",notes = "Devuelve todos los conductores")
    public ResponseEntity<?> getConductores(){
        try {
            return new ResponseEntity<>(lottowebService.getConductores(),HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>("No existe registro de conductores", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/sort/{tipo}")
    @ApiOperation(value = "Obtiene todos los conductores en un orden específico",notes = "Devuelve todos los conductores en un orden específico")
    public ResponseEntity<?> getConductoresSort(@PathVariable("tipo") String tipo){
        try{
            return new ResponseEntity<>(lottowebService.getConductoresOrder(tipo), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("No existe registro de conductores", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{documento}/{tdoc}")
    @ApiOperation(value = "Obtiene un conductor",notes = "Devuelve un conductor por documento y tipo de documento")
    public ResponseEntity<?> getConductor(@PathVariable String documento, @PathVariable String tdoc){
        try {
            return new ResponseEntity<>(lottowebService.getConductor(documento,tdoc), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>("El conductor solicitado no existe", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/whoami")
    @ApiOperation(value = "Obtiene informacion del conductor autenticado", notes = "El conductor solicitado debe estar autenticado")
    public ResponseEntity<?> getConductor(@RequestHeader("Authorization") String token){
        try{
            String correo = jwtService.user(token);
            if(correo.length() > 0){
                Conductor conductor = lottowebService.getConductor(correo);
                return new ResponseEntity<>(conductor,HttpStatus.OK);
            }else {
                return new ResponseEntity<>("No autenticado", HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
            }

        }catch (Exception e){
            return new ResponseEntity<>("Error en la solicitud", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/login/{correo}/{password}")
    public ResponseEntity<?> authenticateUser(@PathVariable String correo, @PathVariable String password) {
        try{
            Conductor ps = lottowebService.getConductor(correo);
            if(ps!=null && ps.getPassword().equals(password)){
                List<String> roles = new ArrayList<>();
                roles.add("cliente");
                String tk = jwtService.createToken(correo,roles);
                System.out.println(tk);
                return new ResponseEntity<>(tk, HttpStatus.OK);
            }
            else if(ps != null && ps.getPassword().equals(password)){
                List<String> roles = new ArrayList<>();
                roles.add("conductor");
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
    public ResponseEntity<?> registrar(@PathVariable String correo, @PathVariable String password, @PathVariable String nombre, @PathVariable String cedula, @PathVariable String telefono) {
        try {
            System.out.println("yaaaaaaaaaaaaaaaaaaaaaaaaa");
            Conductor conductor = new Conductor();
            conductor.setCorreo(correo);
            conductor.setPassword(password);
            conductor.setNombre(nombre);
            conductor.setTipoDocumento("cedula");
            conductor.setDocumento(cedula);
            conductor.setTelefono(telefono);
            conductor.setCalificacion(0);
            System.out.println("sera que si");
            lottowebService.saveConductor(conductor);
            List<String> roles = new ArrayList<>();
            roles.add("cliente");
            String tok = jwtService.createToken(conductor.getCorreo(), roles);
            return new ResponseEntity<>(tok, HttpStatus.ACCEPTED);
        } catch (ExceptionServiciosLottoWeb e) {
            e.printStackTrace();
            return new ResponseEntity<>("Rechazo", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/registrarEnOferta/{oferta}")
    public ResponseEntity<?> registrarEnOferta(@RequestHeader("Authorization") String token, @PathVariable int oferta){
        try{
            String correo = jwtService.user(token);
            if(correo.length() > 0){
                Conductor conductor = this.lottowebService.getConductor(correo);
                Oferta o = this.lottowebService.getOferta(oferta);
                this.lottowebService.entrarAOferta(conductor,o);
                List<Conductor> conductores = this.lottowebService.getConductoresEnOferta(o);
                return new ResponseEntity<>(conductores, HttpStatus.OK);
            }else{
                return new ResponseEntity<>("No autenticado", HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
            }
        }catch (Exception e){
            return new ResponseEntity<>("Error en la solicitud", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizarConductor(@Valid @RequestBody Conductor conductor){
        try{
            System.out.println("888888888888888888888 "+conductor.getCalificacion());
            this.lottowebService.actualizarConductor(conductor);
            return new ResponseEntity<>(conductor, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error en la solicitud", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
