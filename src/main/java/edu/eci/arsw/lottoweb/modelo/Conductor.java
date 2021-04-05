package edu.eci.arsw.lottoweb.modelo;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * ---------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------------------------------------------------------------------------------------------
 * 													CLASE: Conductor
 * ---------------------------------------------------------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------------------------------------------------------
 * @author Santiago Buitrago
 * @author Adriana Catañeda
 * @author Guillermo Castro
 * @version 1.0
 * ---------------------------------------------------------------------------------------------------------------------------
 */

@ApiModel("Model Conductor")
public class Conductor implements Serializable {

    @ApiModelProperty(value = "el documento del conductor (Hace parte del la primaria compuesta)", required = true)
    private int documento;
    @ApiModelProperty(value = "el tipo de documeto del conductor (Hace parte de la primaria compuesta)", required = true)
    private String tipoDocumento;
    @ApiModelProperty(value = "el nombre del conductor", required = true)
    private String nombre;
    @ApiModelProperty(value = "el correo del conductor", required = true)
    private String correo;
    @ApiModelProperty(value = "el telefono del conductor", required = true)
    private int telefono;
    @ApiModelProperty(value = "Password del conductor", required = true)
    private String password;
    @ApiModelProperty(value = "Calificacion ponderada del servicio del condcutor", required = true)
    private int calificacion;
    @ApiModelProperty(value = "Vehiculo del conductor", required = true)
    private Vehiculo vehiculoServicio;
    @ApiModelProperty(value = "Viajes realizados por el conductor", required = true)
    private ArrayList<Viaje> viajes;
    @ApiModelProperty(value = "Ofertas capturadas por el conductor", required = true)
    private ArrayList<Oferta> ofertas;

    public Conductor(int documento, String tipoDocumento, String nombre, String correo, int telefono, String password){
        this.documento=documento;
        this.tipoDocumento=tipoDocumento;
        this.nombre=nombre;
        this.correo= correo;
        this.telefono= telefono;
        this.password= password;
        this.calificacion=calificacion;
        this.vehiculoServicio= vehiculoServicio;
        viajes= new ArrayList<Viaje>();
        ofertas= new ArrayList<Oferta>();
    }
    public Conductor(){}

    public Conductor getCliente(String correo){
        return this;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public Vehiculo getVehiculoServicio() {
        return vehiculoServicio;
    }

    public void setVehiculoServicio(Vehiculo vehiculoServicio) {
        this.vehiculoServicio = vehiculoServicio;
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

    public Ubicacion getUbicacion() {
        return vehiculoServicio.getUbicacion();
    }
}