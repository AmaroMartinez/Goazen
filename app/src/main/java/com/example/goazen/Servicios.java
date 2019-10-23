package com.example.goazen;

import java.util.ArrayList;

public class Servicios {

    private String Nombre_servicio;
    private boolean Enable;
    private double precio;

    public Servicios(String ns, boolean e, double p){

        this.Nombre_servicio = ns;
        this.Enable = e;
        this.precio = p;

    }

    public String getNombre_servicio() {
        return Nombre_servicio;
    }

    public void setNombre_servicio(String nombre_servicio) {
        Nombre_servicio = nombre_servicio;
    }

    public boolean isEnable() {
        return Enable;
    }

    public void setEnable(boolean enable) {
        Enable = enable;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
