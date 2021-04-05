package edu.eci.arsw.lottoweb.controlador;

import edu.eci.arsw.lottoweb.modelo.Oferta;
import edu.eci.arsw.lottoweb.servicios.ExceptionServiciosLottoWeb;
import edu.eci.arsw.lottoweb.servicios.ServiceLottoWeb;
import edu.eci.arsw.lottoweb.servicios.impl.JwtService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public class ControladorOfertas {

    @Autowired
    private final ServiceLottoWeb lottowebService;
    @Autowired
    private JwtService jwtService;

    public ControladorOfertas(ServiceLottoWeb lottowebService) {
        this.lottowebService = lottowebService;
    }

    @GetMapping("/")
    @ApiOperation(value = "Obtine todas las ofertas",notes = "Devuelve todas las ofertas")
    public ResponseEntity<?> getOfertas(){
        try {
            return new ResponseEntity<>(lottowebService.getOfertas(), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>("No existe registro de ofertas", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Encuentra una oferta",notes = "Devuelve una oferta por id")
    public ResponseEntity<?> getOferta(@PathVariable int id){
        try {
            return new ResponseEntity<>(lottowebService.getOferta(id), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>("El paseador solicitado no existe", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/iniciadas")
    public ResponseEntity<?> getOfertasIniciadas(@RequestHeader("Authorization") String token){
        try{
            String correo = jwtService.user(token);
            if(correo.length() > 0){
                return new ResponseEntity<>(this.lottowebService.getOfertasIniciadas(), HttpStatus.OK);
            }else{
                return new ResponseEntity<>("No autorizado",HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
            }
        }catch (Exception e){
            return new ResponseEntity<>("El paseador solicitado no existe", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{oferta}/subastas")
    public ResponseEntity<?> getSubastasOferta(@PathVariable int oferta){
        try{
            Oferta oferta1 = new Oferta();
            oferta1.setId(oferta);
            System.out.println(this.lottowebService.getSubastasOferta(oferta1).size() +" siiiiiiii");
            return new ResponseEntity<>(this.lottowebService.getSubastasOferta(oferta1), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("El paseador solicitado no existe", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<?> putOferta(@Valid @RequestBody Oferta oferta){
        try{
            lottowebService.actualizarOferta(oferta);
            return new ResponseEntity<>(oferta, HttpStatus.ACCEPTED);
        } catch (ExceptionServiciosLottoWeb exceptionServiciosEasyCare) {
            exceptionServiciosEasyCare.printStackTrace();
            return new ResponseEntity<>("No se pudo actualizar la oferta", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
