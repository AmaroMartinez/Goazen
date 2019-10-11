package com.example.goazen;

public class Servicio {

    public String  Titulo;
    public String Hora;
    public String Id;
    public String Trabajador;
    public String Precio;


    public Servicio() {
        // Default constructor required for calls to DataSnapshot.getValue(Servicio.class)
    }

    public Servicio(String Titulo, String Hora, String Id, String Trabajador, String Precio ) {
        this.Titulo = Titulo;
        this.Hora = Hora;
        this.Id = Id;
        this.Trabajador = Trabajador;
        this.Precio = Precio;

    }

}
