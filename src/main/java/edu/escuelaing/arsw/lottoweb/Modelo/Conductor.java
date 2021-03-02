package edu.escuelaing.arsw.lottoweb.Modelo;

public class Conductor {
    private String documento;
    private String tipoDocumento;
    private String nombre;
    private String correo;
    private String telefono;
    private String contraseña;
    private float calificacion;

    public Conductor(String documento,String tipoDocumento,String nombre,String correo,String telefono,String contraseña,float calificacion){
        this.documento=documento;
        this.tipoDocumento=tipoDocumento;
        this.nombre=nombre;
        this.correo=correo;
        this.telefono=telefono;
        this.contraseña=contraseña;
        this.calificacion=calificacion;
    }

}
