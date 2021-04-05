package edu.eci.arsw.lottoweb.modelo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * ---------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------------------------------------------------------------------------------------------
 * 													CLASE: Subasta
 * ---------------------------------------------------------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------------------------------------------------------
 * @author Santiago Buitrago
 * @author Adriana Catañeda
 * @author Guillermo Castro
 * @version 1.0
 * ---------------------------------------------------------------------------------------------------------------------------
 */

@ApiModel("Model Subasta")
public class Subasta {

    @ApiModelProperty(value = "Valor que se dió como resultado de la subasta", required = true)
    private Oferta oferta;
    @ApiModelProperty(value = "Subasta realizada por el Conductor", required = true)
    private Conductor conductor;
    @ApiModelProperty(value = "Valor que se dió como resultado de la subasta", required = true)
    private int subasta;

    public Oferta getOferta() {
        return oferta;
    }
    public void setOferta(Oferta oferta) {
        this.oferta = oferta;
    }

    public Conductor getConductor() {
        return conductor;
    }
    public void setConductor(Conductor conductor) {
        this.conductor = conductor;
    }
    public int getSubasta() {
        return subasta;
    }
    public void setSubasta(int subasta) {
        this.subasta = subasta;
    }

    public void setCreador(Cliente cliente) {
    }

    public void setViaje(Viaje viaje) {
    }

    public void setPermitirMasVehiculos(boolean aBoolean) {
    }

    public void setNumVehiculos(int anInt) {
    }

    public void setId(int numSubasta) {
    }

    public List<Conductor> getconductores() {
        return null;
    }

    public Conductor getCreador() {
        return conductor;
    }

    public int getId() {
        int id=0;
        return id;
    }

    public Viaje getViaje() {
        return null;
    }

    public boolean getPermitirMasVehiculos() {
        return true;
    }

    public List<Conductor> getConductores() {
        List<Conductor> c= new List<Conductor>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Conductor> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Conductor conductor) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Conductor> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends Conductor> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public boolean equals(Object o) {
                return false;
            }

            @Override
            public int hashCode() {
                return 0;
            }

            @Override
            public Conductor get(int index) {
                return null;
            }

            @Override
            public Conductor set(int index, Conductor element) {
                return null;
            }

            @Override
            public void add(int index, Conductor element) {

            }

            @Override
            public Conductor remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<Conductor> listIterator() {
                return null;
            }

            @Override
            public ListIterator<Conductor> listIterator(int index) {
                return null;
            }

            @Override
            public List<Conductor> subList(int fromIndex, int toIndex) {
                return null;
            }
        };
        return c;
    }
}
