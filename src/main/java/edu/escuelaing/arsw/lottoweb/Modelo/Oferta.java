package edu.escuelaing.arsw.lottoweb.Modelo;

import java.util.ArrayList;
import java.util.List;

public class Oferta {
    private int id;
    private int oferta;
    private Cliente creador;
    private Viaje viaje;
    private int numMascotas;
    private boolean permitirMasVehiculos;
    List<Conductor> conductores = new ArrayList<Conductor>();
    public Oferta(){

    }

}
