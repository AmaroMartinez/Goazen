package com.example.goazen;

import android.app.Application;
import android.graphics.Color;
import android.util.Log;

import androidx.annotation.NonNull;

import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


import static android.content.ContentValues.TAG;

public class EventosCalendario extends Application {

    //Declaramos los objetos necesarios para poder leer los eventos.

    private static String fecha;
    private static String tarea;
    private static String dni_trabajador;

    public EventosCalendario(String f, String t){
        this.fecha = f;
        this.tarea = t;
    }
    private static ArrayList<EventosCalendario> listaEventos;

    private static FirebaseFirestore db;


    @Override
    public void onCreate() {
        super.onCreate();
    }
    /*Leemos de la base de datos los eventos que
    * pertenecen al */
    public static void readEventos(){

        //Cojemos el dni del trabajador logado
        dni_trabajador = DatosUsuario.getDNI();

        //Inicializamos
        db = FirebaseFirestore.getInstance();
        listaEventos = new ArrayList<EventosCalendario>();

        //Leemos todos y los metemos en un array
        db.collection("Evento")
                .whereEqualTo("Trabajador", dni_trabajador)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.get("Fecha") + " => " + document.get("Titulo") + " => " + document.get("Trabajador"));
                                listaEventos.add(new EventosCalendario(document.get("Fecha").toString(),document.get("Titulo").toString()));
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

    }

    public static String getFecha() {
        return fecha;
    }

    public static void setFecha(String fecha) {
        EventosCalendario.fecha = fecha;
    }

    public static String getTarea() {
        return tarea;
    }

    public static void setTarea(String tarea) {
        EventosCalendario.tarea = tarea;
    }

    public static String getDni_trabajador() {
        return dni_trabajador;
    }

    public static void setDni_trabajador(String dni_trabajador) {
        EventosCalendario.dni_trabajador = dni_trabajador;
    }

    public static ArrayList<EventosCalendario> getListaEventos() {
        return listaEventos;
    }

    public static void setListaEventos(ArrayList<EventosCalendario> listaEventos) {
        EventosCalendario.listaEventos = listaEventos;
    }
}
