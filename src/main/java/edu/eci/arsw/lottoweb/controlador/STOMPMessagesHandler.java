package edu.eci.arsw.lottoweb.controlador;

import edu.eci.arsw.lottoweb.modelo.*;
import edu.eci.arsw.lottoweb.servicios.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ---------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------------------------------------------------------------------------------------------
 * 													CLASE: STOMPMessagesHandler
 * ---------------------------------------------------------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------------------------------------------------------
 * @author Santiago Buitrago
 * @author Adriana Catañeda
 * @author Guillermo Castro
 * @version 1.0
 * ---------------------------------------------------------------------------------------------------------------------------
 */


@Controller
public class STOMPMessagesHandler {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private ServiceLottoWeb serviceLottoWeb;

    private Timer timer;
    private TimerTask timerTask;
    private ConcurrentHashMap<String, Oferta> sesionesOfertaCreadores = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, List<Object>> sesionesOfertaconductores = new ConcurrentHashMap<>();

    @MessageMapping("/nuevaOferta")
    public void nuevaOfertaEvent(String datos, @Header("simpSessionId") String sessionId) throws ExceptionServiciosLottoWeb {
        System.out.println(datos);
        System.out.println(sessionId + " ######################");
        JSONObject json = new JSONObject(datos);
        Oferta oferta = new Oferta();
        Cliente cliente = new Cliente();
        cliente.setDocumento(json.getJSONObject("oferta").getJSONObject("creador").getString("documento"));
        cliente.setCorreo(json.getJSONObject("oferta").getJSONObject("creador").getString("correo"));
        cliente.setTipoDocumento(json.getJSONObject("oferta").getJSONObject("creador").getString("tipoDocumento"));
        cliente.setTelefono(json.getJSONObject("oferta").getJSONObject("creador").getString("telefono"));
        cliente.setNombre(json.getJSONObject("oferta").getJSONObject("creador").getString("nombre"));
        System.out.println(json.getJSONObject("oferta").getJSONObject("creador")+" >>>>>>>>>>>>>>>");
        Ruta ruta = new Ruta();
        Viaje viaje = new Viaje();
        viaje.setRuta(ruta);
        viaje.setDuracion(json.getJSONObject("oferta").getJSONObject("paseo").getInt("duracion"));
        viaje.setEspecificaciones(json.getJSONObject("oferta").getJSONObject("paseo").getString("especificaciones"));
        viaje.setPrecio(json.getJSONObject("oferta").getJSONObject("paseo").getInt("precio"));
        oferta.setCreador(cliente);
        oferta.setViaje(viaje);
        String latitud = String.valueOf(json.getDouble("latitud"));
        String longitud = String.valueOf(json.getDouble("longitud"));
        this.sesionesOfertaCreadores.put(sessionId,oferta);
        this.serviceLottoWeb.saveOferta(oferta, latitud, longitud);
        this.simpMessagingTemplate.convertAndSend("/topic/ofertas",oferta);
        this.serviceLottoWeb.addOferta(oferta);
    }

    @MessageMapping("/cerrarOferta.{numOferta}")
    public void cerrarOfertaEvent(@DestinationVariable int numOferta){
        try{
            Oferta sub = this.serviceLottoWeb.getOferta(numOferta);
            this.serviceLottoWeb.cerrarOferta(numOferta);
            this.simpMessagingTemplate.convertAndSend("/topic/ofertas/cerrar", sub);
            this.simpMessagingTemplate.convertAndSend("/topic/cerrar/oferta."+numOferta, sub);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @MessageMapping("/oferta.{numOferta}")
    public void ofertaEvent(Conductor conductor, @DestinationVariable int numOferta, @Header("simpSessionId") String sessionId) throws ExceptionServiciosLottoWeb {
        List par = new ArrayList();
        par.add(numOferta);
        par.add(conductor);
        this.sesionesOfertaconductores.put(sessionId,par);
        System.out.println(conductor.getUbicacion().getLatitud() + " }}}}}}}}}}}}}}}}}}}}}}}}}");
        Oferta oferta = new Oferta();
        oferta.setId(numOferta);
        this.serviceLottoWeb.entrarAOferta(conductor, oferta);
        this.simpMessagingTemplate.convertAndSend("/topic/oferta."+numOferta, conductor);
    }

    @MessageMapping("/saliroferta.{numOferta}")
    public void salirOfertaEvent(Conductor conductor, @DestinationVariable int numOferta, @Header("simpSessionId") String sessionId) throws ExceptionServiciosLottoWeb {
        this.sesionesOfertaconductores.remove(sessionId);
        System.out.println("eliminando conductor de oferta: "+numOferta);
        Oferta oferta = new Oferta();
        oferta.setId(numOferta);
        this.serviceLottoWeb.salirDeOferta(conductor,oferta);
        this.simpMessagingTemplate.convertAndSend("/topic/eliminarconductor/oferta."+numOferta, conductor);
    }

    @MessageMapping("/agregarsubasta/oferta.{numOferta}")
    public void agregarSubastaOferta(String datos, @DestinationVariable int numOferta){
        try{
            System.out.println(datos);
            JSONObject json = new JSONObject(datos);
            Oferta oferta = new Oferta();
            oferta.setId(json.getJSONObject("oferta").getInt("id"));
            Conductor conductor = this.serviceLottoWeb.getConductor(json.getJSONObject("ofertor").getString("correo"));
            int subasta = json.getInt("subasta");
            this.serviceLottoWeb.agregarSubastaOferta(oferta,conductor,subasta);
            this.simpMessagingTemplate.convertAndSend("/topic/agregarsubasta/oferta."+numOferta,datos);
        }catch (ExceptionServiciosLottoWeb ExceptionServiciosLottoWeb){
            ExceptionServiciosLottoWeb.printStackTrace();
        }
    }

    @MessageMapping("/elegirconductor/{idOferta}/{lat}/{lng}")
    public void aceptarSubasta(Conductor conductorSeleccionado, @DestinationVariable int idOferta, @DestinationVariable double lat, @DestinationVariable double lng) throws ExceptionServiciosLottoWeb {
        Oferta sub = this.serviceLottoWeb.getOfertaIniciada(idOferta);
        System.out.println(this.serviceLottoWeb.getOfertaIniciada(idOferta)+" &&&&&&&&&&&&&&&&&&&&&&");
        sub.getConductores().forEach(conductor -> {
            if(conductor.getCorreo().equals(conductorSeleccionado.getCorreo())){
                System.out.println("(((((((((((((((((((())))))))))))))))))))");
                this.simpMessagingTemplate.convertAndSend("/topic/decisionOferta/"+conductorSeleccionado.getCorreo(),"{\"seleccionado\" : \"true\", \"lat\" : "+lat+", \"lng\" : "+lng+"}");
            }else{
                System.out.println("++++++++++++++++++++++++++++");
                this.simpMessagingTemplate.convertAndSend("/topic/decisionOferta/"+conductorSeleccionado.getCorreo(),"{\"seleccionado\" : \"false\"}");
            }
        });
        this.simpMessagingTemplate.convertAndSend("/topic/ofertas/cerrar", sub);
        this.serviceLottoWeb.cerrarOferta(idOferta);
    }

    @MessageMapping("/actualizarUbicacionconductor/{lat}/{lng}")
    public void actualizarUbicacionconductor(Oferta oferta, @DestinationVariable double lat, @DestinationVariable double lng){
        this.simpMessagingTemplate.convertAndSend("/topic/actualizarUbicacion."+oferta.getCreador().getCorreo(), "{\"lat\" : "+lat+", \"lng\" : "+lng+" }");
    }

    @MessageMapping("/actualizarUbicacionCliente/{lat}/{lng}/{oferta}")
    public void actualizarUbicacionCliente(Conductor conductor, @DestinationVariable double lat, @DestinationVariable double lng, @DestinationVariable int oferta){
        this.simpMessagingTemplate.convertAndSend("/topic/actualizarUbicacion."+conductor.getCorreo(), "{\"lat\" : "+lat+", \"lng\" : "+lng+", \"oferta\" : "+oferta+" }");
    }

    @MessageMapping("/cancelarPaseo")
    public void cancelarPaseo(Oferta oferta){
        this.simpMessagingTemplate.convertAndSend("/topic/cancelarPaseo."+oferta.getCreador().getCorreo(), oferta);
    }

    @MessageMapping("/comenzarPaseoVivo")
    public void comenzarPaseoVivo(Oferta oferta){
        this.simpMessagingTemplate.convertAndSend("/topic/comenzarPaseoVivo."+oferta.getCreador().getCorreo(),oferta);
    }

    @MessageMapping("/finalizarPaseo")
    public void finalizarPaseo(Oferta oferta){
        this.simpMessagingTemplate.convertAndSend("/topic/finPaseo."+oferta.getCreador().getCorreo(), oferta);
    }

    @EventListener
    private void handleSessionConnect(SessionConnectedEvent event){
        System.out.println("Conectadoooooooooooooooo "+event.getMessage().getHeaders());
        System.out.println("Conectadoooooooooooooooo "+event.getUser());
        System.out.println("Conectadoooooooooooooooo "+event.getSource());
        System.out.println("Conectadoooooooooooooooo "+event.getTimestamp());
        System.out.println("Conectadoooooooooooooooo "+event.getMessage());
    }

    @EventListener
    private void handleSessionDesconnect(SessionDisconnectEvent event) throws ExceptionServiciosLottoWeb {
        if(this.sesionesOfertaCreadores.get(event.getSessionId())!=null){
            int id = this.sesionesOfertaCreadores.get(event.getSessionId()).getId();
            System.out.println(event.getSessionId() + " oooooooooooooooooooooo");
            this.serviceLottoWeb.cerrarOferta(id);
            this.simpMessagingTemplate.convertAndSend("/topic/ofertas/cerrar", this.sesionesOfertaCreadores.get(event.getSessionId()));
            this.simpMessagingTemplate.convertAndSend("/topic/cerrar/oferta."+id,
                    this.sesionesOfertaCreadores.get(event.getSessionId()));
            this.sesionesOfertaCreadores.remove(event.getSessionId());
        }else if (this.sesionesOfertaconductores.get(event.getSessionId()) != null){
            int numOferta = (Integer) this.sesionesOfertaconductores.get(event.getSessionId()).get(0);
            Conductor conductor = (Conductor)this.sesionesOfertaconductores.get(event.getSessionId()).get(1);
            Oferta oferta = this.serviceLottoWeb.getOferta(numOferta);
            this.serviceLottoWeb.salirDeOferta(conductor,oferta);
            this.simpMessagingTemplate.convertAndSend("/topic/eliminarconductor/oferta."+numOferta, conductor);
            this.simpMessagingTemplate.convertAndSend("/topic/cancelarPaseo."+oferta.getCreador().getCorreo(), oferta);
        }
    }

    @SubscribeMapping("/oferta/{numOferta}/{user}")
    private void handleSessionSubscription(@DestinationVariable int numOferta, @DestinationVariable String user){
        System.out.println("Conectadoooooooooooooooo Subscriptoooooooooooooorrrrrrrrrrrrrrr "+user+" "+numOferta);
    }

    @EventListener
    private void handleSessionSubscription(SessionUnsubscribeEvent event){
        System.out.println("Desconectadooooooooooooooooooo Subscriptoooooooooooooooooooooooor "+event.getMessage().getHeaders().get("sessionId") + " ");
    }
}
