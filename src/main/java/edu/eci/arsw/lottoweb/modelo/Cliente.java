package edu.eci.arsw.lottoweb.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * ---------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------------------------------------------------------------------------------------------
 * 													CLASE: LottoWebApp
 * ---------------------------------------------------------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------------------------------------------------------
 * @author Santiago Buitrago
 * @author Adriana Catañeda
 * @author Guillermo Castro
 * @version 1.0
 * ---------------------------------------------------------------------------------------------------------------------------
 */

@ApiModel("Model Cliente")
public class Cliente implements Serializable{

    @ApiModelProperty(value = "el documento del cliente (Hace parte del la primaria compuesta)", required = true)
    private String documento;
    @ApiModelProperty(value = "el tipo de documeto del cliente (Hace parte de la primaria compuesta)", required = true)
    private String tipoDocumento;
    @ApiModelProperty(value = "el nombre del cliente", required = true)
    private String nombre;
    @ApiModelProperty(value = "el correo del cliente", required = true)
    private String correo;
    @ApiModelProperty(value = "el telefono del cliente", required = true)
    private String telefono;
    @ApiModelProperty(value = "Password del cliente", required = true)
    private String password;
    @ApiModelProperty(value = "Rutas que ha establecido el cliente", required = true)
    private ArrayList<Ruta> rutas;
    @ApiModelProperty(value = "Ofertas realizadas por el cliente", required = true)
    private ArrayList<Oferta> ofertas;
    @ApiModelProperty(value = "Viajes del cliente", required = true)
    private ArrayList<Viaje> viajes;

    public Cliente(String documento, String tipoDocumento, String nombre, String correo, String telefono, String password){
        this.documento=documento;
        this.tipoDocumento=tipoDocumento;
        this.nombre=nombre;
        this.correo=correo;
        this.telefono=telefono;
        this.password=password;
        rutas= new ArrayList<Ruta>();
        viajes= new ArrayList<Viaje>();
        ofertas= new ArrayList<Oferta>();
    }
    public Cliente(){}

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo(){
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public ArrayList<Ruta> getRutas() {
        return rutas;
    }

    public void setRutas(ArrayList<Ruta> rutas) {
        this.rutas = rutas;
    }

    public ArrayList<Viaje> getViajes() {
        return viajes;
    }

    public void setViajes(ArrayList<Viaje> viajes) {
        this.viajes = viajes;
    }

    public ArrayList<Oferta> getOfertas() {
        return ofertas;
    }

    public void setOfertas(ArrayList<Oferta> ofertas) {
        this.ofertas = ofertas;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
