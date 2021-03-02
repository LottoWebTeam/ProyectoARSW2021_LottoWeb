package edu.escuelaing.arsw.lottoweb.Modelo;

import java.util.ArrayList;

public class Cliente {
    private String documento;
    private String tipoDocumento;
    private String nombre;
    private String correo;
    private String telefono;
    private String contraseña;
    private ArrayList<Ruta> rutas;
    private ArrayList<Vehiculo> vehiculos;
    private ArrayList<Oferta> ofertas;
    private ArrayList<Viaje> viajes;


    public Cliente(String documento,String tipoDocumento,String nombre,String correo,String telefono,String contraseña,ArrayList<Ruta> rutas,ArrayList<Vehiculo> vehiculos,ArrayList<Oferta> ofertas,ArrayList<Viaje> viajes){
        this.documento=documento;
        this.tipoDocumento=tipoDocumento;
        this.nombre=nombre;
        this.correo=correo;
        this.telefono=telefono;
        this.contraseña=contraseña;
        this.rutas=rutas;
        this.vehiculos=vehiculos;
        this.ofertas=ofertas;
        this.viajes=viajes;
    }
}
