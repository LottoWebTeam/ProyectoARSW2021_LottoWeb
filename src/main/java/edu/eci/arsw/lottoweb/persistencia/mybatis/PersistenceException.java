package edu.eci.arsw.lottoweb.persistencia.mybatis;

public class PersistenceException extends Exception{


        public static final String NO_ENCONTRADO = "no se logro encontrar lo requerido";
        public static final String NO_AGREGADO = "no se logro agregar lo solicitado";
        public static final String ERROR_EN_LA_SOLICITUD = "se presento un error en la solicitud";

        public PersistenceException(String string){

        }
}
