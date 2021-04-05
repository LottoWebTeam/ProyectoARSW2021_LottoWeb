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
import org.springframework.web.bind.annotation.GetMapping;
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
    private ConcurrentHashMap<String, Subasta> sesionesSubastaCreadores = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, List<Object>> sesionesSubastaconductores = new ConcurrentHashMap<>();

    @MessageMapping("/nuevaSubasta")
    public void nuevaSubastaEvent(String datos, @Header("simpSessionId") String sessionId){
        try {
            System.out.println(datos);
            System.out.println(sessionId + " ######################");
            JSONObject json = new JSONObject(datos);
            Subasta subasta = new Subasta();
            Cliente cliente = new Cliente();
            cliente.setDocumento(json.getJSONObject("subasta").getJSONObject("creador").getString("documento"));
            cliente.setCorreo(json.getJSONObject("subasta").getJSONObject("creador").getString("correo"));
            cliente.setTipoDocumento(json.getJSONObject("subasta").getJSONObject("creador").getString("tipoDocumento"));
            cliente.setTelefono(json.getJSONObject("subasta").getJSONObject("creador").getString("telefono"));
            cliente.setNombre(json.getJSONObject("subasta").getJSONObject("creador").getString("nombre"));
            System.out.println(json.getJSONObject("subasta").getJSONObject("creador")+" >>>>>>>>>>>>>>>");
            Ruta ruta = new Ruta();
            Viaje viaje = new Viaje();
            viaje.setRuta(ruta);
            viaje.setDuracion(json.getJSONObject("subasta").getJSONObject("paseo").getInt("duracion"));
            viaje.setEspecificaciones(json.getJSONObject("subasta").getJSONObject("paseo").getString("especificaciones"));
            viaje.setPrecio(json.getJSONObject("subasta").getJSONObject("paseo").getInt("precio"));
            subasta.setCreador(cliente);
            subasta.setViaje(viaje);
            subasta.setNumVehiculos(json.getJSONObject("subasta").getInt("NumVehiculos"));
            subasta.setPermitirMasVehiculos(json.getJSONObject("subasta").getBoolean("PermitirMasVehiculos"));
            String latitud = String.valueOf(json.getDouble("latitud"));
            String longitud = String.valueOf(json.getDouble("longitud"));
            this.sesionesSubastaCreadores.put(sessionId,subasta);
            this.serviceLottoWeb.saveSubasta(subasta, latitud, longitud);
            this.simpMessagingTemplate.convertAndSend("/topic/subastas",subasta);
            this.serviceLottoWeb.addSubasta(subasta);
        } catch (ExceptionServiciosLottoWeb ExceptionServiciosLottoWeb) {
            ExceptionServiciosLottoWeb.printStackTrace();
        }
    }

    @MessageMapping("/cerrarSubasta.{numSubasta}")
    public void cerrarSubastaEvent(@DestinationVariable int numSubasta){
        try{
            Subasta sub = this.serviceLottoWeb.getSubasta(numSubasta);
            this.serviceLottoWeb.cerrarSubasta(numSubasta);
            this.simpMessagingTemplate.convertAndSend("/topic/subastas/cerrar", sub);
            this.simpMessagingTemplate.convertAndSend("/topic/cerrar/subasta."+numSubasta, sub);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @MessageMapping("/subasta.{numSubasta}")
    public void subastaEvent(Conductor conductor, @DestinationVariable int numSubasta, @Header("simpSessionId") String sessionId){
        try {
            List par = new ArrayList();
            par.add(numSubasta); par.add(conductor);
            this.sesionesSubastaconductores.put(sessionId,par);
            System.out.println(conductor.getUbicacion().getLatitud() + " }}}}}}}}}}}}}}}}}}}}}}}}}");
            Subasta subasta = new Subasta();
            subasta.setId(numSubasta);
            this.serviceLottoWeb.entrarASubasta(conductor, subasta);
            this.simpMessagingTemplate.convertAndSend("/topic/subasta."+numSubasta, conductor);
        } catch (ExceptionServiciosLottoWeb ExceptionServiciosLottoWeb) {
            ExceptionServiciosLottoWeb.printStackTrace();
        }
    }

    @MessageMapping("/salirsubasta.{numSubasta}")
    public void salirSubastaEvent(Conductor conductor, @DestinationVariable int numSubasta, @Header("simpSessionId") String sessionId){
        try{
            this.sesionesSubastaconductores.remove(sessionId);
            System.out.println("eliminando conductor de subasta: "+numSubasta);
            Subasta subasta = new Subasta();
            subasta.setId(numSubasta);
            this.serviceLottoWeb.salirDeSubasta(conductor,subasta);
            this.simpMessagingTemplate.convertAndSend("/topic/eliminarconductor/subasta."+numSubasta, conductor);
        }catch (ExceptionServiciosLottoWeb ExceptionServiciosLottoWeb){
            ExceptionServiciosLottoWeb.printStackTrace();
        }
    }

    @MessageMapping("/agregaroferta/subasta.{numSubasta}")
    public void agregarOfertaSubasta(String datos, @DestinationVariable int numSubasta){
        try{
            System.out.println(datos);
            JSONObject json = new JSONObject(datos);
            Subasta subasta = new Subasta();
            subasta.setId(json.getJSONObject("subasta").getInt("id"));
            Conductor conductor = this.serviceLottoWeb.getconductor(json.getJSONObject("ofertor").getString("correo"));
            int oferta = json.getInt("oferta");
            this.serviceLottoWeb.agregarOfertaSubasta(subasta,conductor,oferta);
            this.simpMessagingTemplate.convertAndSend("/topic/agregaroferta/subasta."+numSubasta,datos);
        }catch (ExceptionServiciosLottoWeb ExceptionServiciosLottoWeb){
            ExceptionServiciosLottoWeb.printStackTrace();
        }
    }

    @MessageMapping("/elegirconductor/{idSubasta}/{lat}/{lng}")
    public void aceptarOferta(Conductor conductorSeleccionado, @DestinationVariable int idSubasta, @DestinationVariable double lat, @DestinationVariable double lng){
        try {
            Subasta sub = this.serviceLottoWeb.getSubastaIniciada(idSubasta);
            System.out.println(this.serviceLottoWeb.getSubastaIniciada(idSubasta)+" &&&&&&&&&&&&&&&&&&&&&&");
            sub.getconductores().forEach(conductor -> {
                if(conductor.getCorreo().equals(conductorSeleccionado.getCorreo())){
                    System.out.println("(((((((((((((((((((())))))))))))))))))))");
                    this.simpMessagingTemplate.convertAndSend("/topic/decisionSubasta/"+conductorSeleccionado.getCorreo(),"{\"seleccionado\" : \"true\", \"lat\" : "+lat+", \"lng\" : "+lng+"}");
                }else{
                    System.out.println("++++++++++++++++++++++++++++");
                    this.simpMessagingTemplate.convertAndSend("/topic/decisionSubasta/"+conductorSeleccionado.getCorreo(),"{\"seleccionado\" : \"false\"}");
                }
            });
            this.simpMessagingTemplate.convertAndSend("/topic/subastas/cerrar", sub);
            this.serviceLottoWeb.cerrarSubasta(idSubasta);
        } catch (ExceptionServiciosLottoWeb ExceptionServiciosLottoWeb) {
            ExceptionServiciosLottoWeb.printStackTrace();
        }
    }

    @MessageMapping("/actualizarUbicacionconductor/{lat}/{lng}")
    public void actualizarUbicacionconductor(Subasta subasta, @DestinationVariable double lat, @DestinationVariable double lng){
        this.simpMessagingTemplate.convertAndSend("/topic/actualizarUbicacion."+subasta.getCreador().getCorreo(), "{\"lat\" : "+lat+", \"lng\" : "+lng+" }");
    }

    @MessageMapping("/actualizarUbicacionCliente/{lat}/{lng}/{subasta}")
    public void actualizarUbicacionCliente(Conductor conductor, @DestinationVariable double lat, @DestinationVariable double lng, @DestinationVariable int subasta){
        this.simpMessagingTemplate.convertAndSend("/topic/actualizarUbicacion."+conductor.getCorreo(), "{\"lat\" : "+lat+", \"lng\" : "+lng+", \"subasta\" : "+subasta+" }");
    }

    @MessageMapping("/cancelarPaseo")
    public void cancelarPaseo(Subasta subasta){
        this.simpMessagingTemplate.convertAndSend("/topic/cancelarPaseo."+subasta.getCreador().getCorreo(), subasta);
    }

    @MessageMapping("/comenzarPaseoVivo")
    public void comenzarPaseoVivo(Subasta subasta){
        this.simpMessagingTemplate.convertAndSend("/topic/comenzarPaseoVivo."+subasta.getCreador().getCorreo(),subasta);
    }

    @MessageMapping("/finalizarPaseo")
    public void finalizarPaseo(Subasta subasta){
        this.simpMessagingTemplate.convertAndSend("/topic/finPaseo."+subasta.getCreador().getCorreo(), subasta);
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
    private void handleSessionDesconnect(SessionDisconnectEvent event){
        try {
            if(this.sesionesSubastaCreadores.get(event.getSessionId())!=null){
                int id = this.sesionesSubastaCreadores.get(event.getSessionId()).getId();
                System.out.println(event.getSessionId() + " oooooooooooooooooooooo");
                this.serviceLottoWeb.cerrarSubasta(id);
                this.simpMessagingTemplate.convertAndSend("/topic/subastas/cerrar", this.sesionesSubastaCreadores.get(event.getSessionId()));
                this.simpMessagingTemplate.convertAndSend("/topic/cerrar/subasta."+id,
                        this.sesionesSubastaCreadores.get(event.getSessionId()));
                this.sesionesSubastaCreadores.remove(event.getSessionId());
            }else if (this.sesionesSubastaconductores.get(event.getSessionId()) != null){
                int numSubasta = (Integer) this.sesionesSubastaconductores.get(event.getSessionId()).get(0);
                Conductor conductor = (Conductor)this.sesionesSubastaconductores.get(event.getSessionId()).get(1);
                Subasta subasta = this.serviceLottoWeb.getSubasta(numSubasta);
                this.serviceLottoWeb.salirDeSubasta(conductor,subasta);
                this.simpMessagingTemplate.convertAndSend("/topic/eliminarconductor/subasta."+numSubasta, conductor);
                this.simpMessagingTemplate.convertAndSend("/topic/cancelarPaseo."+subasta.getCreador().getCorreo(), subasta);
            }
        } catch (ExceptionServiciosLottoWeb ExceptionServiciosLottoWeb) {
            ExceptionServiciosLottoWeb.printStackTrace();
        }
    }

    @SubscribeMapping("/subasta/{numSubasta}/{user}")
    private void handleSessionSubscription(@DestinationVariable int numSubasta, @DestinationVariable String user){
        System.out.println("Conectadoooooooooooooooo Subscriptoooooooooooooorrrrrrrrrrrrrrr "+user+" "+numSubasta);
    }

    @EventListener
    private void handleSessionSubscription(SessionUnsubscribeEvent event){
        System.out.println("Desconectadooooooooooooooooooo Subscriptoooooooooooooooooooooooor "+event.getMessage().getHeaders().get("sessionId") + " ");
    }
}
