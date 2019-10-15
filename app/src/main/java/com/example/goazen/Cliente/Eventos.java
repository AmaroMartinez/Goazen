package com.example.goazen.Cliente;

import java.sql.Timestamp;

public class Eventos {

    public Timestamp Fecha_hora;
    public String Nom_servicio;





    public Eventos() {
        // Default constructor required for calls to DataSnapshot.getValue(Eventos.class)
    }

    public Eventos(Timestamp Fecha_hora, String Nom_servicio ) {
        this.Fecha_hora = Fecha_hora;
        this.Nom_servicio = Nom_servicio;



    }


}
