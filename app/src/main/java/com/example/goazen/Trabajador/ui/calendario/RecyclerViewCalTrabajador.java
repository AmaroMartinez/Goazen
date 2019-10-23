package com.example.goazen.Trabajador.ui.calendario;


import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class RecyclerViewCalTrabajador {

    /*PASO 3 --> RECYCLER VIEW*/
    /*Creamos una clase java en la que tendremos
    * la constructora y un array de objetos de si
    * misma*/

    private String st_tarea_trabajador;
    private String st_fecha_trabajo;
    private String st_hora_trabajo;
    private String st_dir_trabajo;

    /*Creamos un arrayList para guardar los eventos*/



    public RecyclerViewCalTrabajador(Date fecha, String titulo, String trabajador, String adress) {
        this.st_tarea_trabajador = titulo;
        this.st_dir_trabajo = adress;

        this.st_hora_trabajo = fecha.getHours() + ":" + fecha.getMinutes();
        this.st_fecha_trabajo = fecha.getDate() + "/" + fecha.getMonth() + "/" + fecha.getYear();
    }

    public static List<RecyclerViewCalTrabajador> lista_Trabajos(Date f) {
        List<RecyclerViewCalTrabajador> lista_Trabajos = new ArrayList<RecyclerViewCalTrabajador>();
        EventosCalendario.setListaEventosDias(EventosCalendario.getListaEventos(),f);
        lista_Trabajos = EventosCalendario.getListaEventosDias();
        return lista_Trabajos;
    }


    public String getSt_tarea_trabajador() {
        return st_tarea_trabajador;
    }

    public void setSt_tarea_trabajador(String st_tarea_trabajador) {
        this.st_tarea_trabajador = st_tarea_trabajador;
    }

    public String getSt_fecha_trabajo() {
        return st_fecha_trabajo;
    }

    public void setSt_fecha_trabajo(String st_fecha_trabajo) {
        this.st_fecha_trabajo = st_fecha_trabajo;
    }

    public String getSt_hora_trabajo() {
        return st_hora_trabajo;
    }

    public void setSt_hora_trabajo(String st_hora_trabajo) {
        this.st_hora_trabajo = st_hora_trabajo;
    }

    public String getSt_dir_trabajo() {
        return st_dir_trabajo;
    }

    public void setSt_dir_trabajo(String st_dir_trabajo) {
        this.st_dir_trabajo = st_dir_trabajo;
    }

    public void setLista_Trabajos(ArrayList<RecyclerViewCalTrabajador> lista_Trabajos) {

        EventosCalendario.readEventos();
        lista_Trabajos = EventosCalendario.getListaEventos();

        Log.d(TAG,lista_Trabajos.toString());
    }

}
