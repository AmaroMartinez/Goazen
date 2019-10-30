package com.example.goazen.Cliente;

import com.example.goazen.DatosUsuario;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Eventos {

    public String Fecha;
    public String Titulo;
    public String Trabajador;
    public String Cliente;
    public String Adress;
    public String NombreEvento;


    public Eventos() {
        // Default constructor required for calls to DataSnapshot.getValue(Eventos.class)
    }

    public Eventos(String Fecha, String Titulo, String Trabajador, String Cliente, String Adress, String NombreEvento ) {

        this.Fecha = Fecha;
        this.Titulo = Titulo;
        this.Trabajador=Trabajador;
        this.Cliente=Cliente;
        this.Adress=Adress;
        this.NombreEvento=NombreEvento;

    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getTrabajador() {
        return Trabajador;
    }

    public void setTrabajador(String trabajador) {
        Trabajador = trabajador;
    }

    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String cliente) {
        Cliente = cliente;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }

    public String getNombreEvento() {
        return NombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        NombreEvento = nombreEvento;
    }
}
