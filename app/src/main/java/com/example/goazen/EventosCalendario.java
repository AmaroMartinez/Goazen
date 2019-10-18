package com.example.goazen;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;


import static android.content.ContentValues.TAG;

public class EventosCalendario extends Application {

    //Declaramos los objetos necesarios para poder leer los eventos.

    private static Date fecha;
    private static String tarea;
    private static String dni_trabajador;

    private static ArrayList<Event> listaEventos;

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
        listaEventos = new ArrayList<Event>();

        //Leemos todos y los metemos en un array
        db.collection("Evento")
                .whereEqualTo("Trabajador", dni_trabajador)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                /*Cogemos el dato fecha de la bbdd y hacemos el casting
                                * a millisegundos para darselo a el evento.*/

                                Timestamp f = (Timestamp) document.get("Fecha");
                                long fecha = f.toDate().getTime();
                                Log.d(TAG, document.get("Fecha") + " => " + document.get("Titulo") + " => " + document.get("Trabajador"));
                                listaEventos.add(new Event(R.color.colorPrimary, fecha,document.get("Titulo")));
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

    }

}
