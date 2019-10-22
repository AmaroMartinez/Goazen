package com.example.goazen.Trabajador.ui.calendario;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.goazen.DatosUsuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;


import static android.content.ContentValues.TAG;

public class EventosCalendario{

    //Declaramos los objetos necesarios para poder leer los eventos.

    private static Date fecha;
    private static String tarea;
    private static String dni_trabajador;

    private static ArrayList<RecyclerViewCalTrabajador> listaEventos;
    private static ArrayList<RecyclerViewCalTrabajador> listaEventosDias;

    private static FirebaseFirestore db;

    /*Leemos de la base de datos los eventos que
    * pertenecen al trabajador logado*/
    public static void readEventos(){

        //Cojemos el dni del trabajador logado
        dni_trabajador = DatosUsuario.getDNI();

        //Inicializamos
        db = FirebaseFirestore.getInstance();
        listaEventos = new ArrayList<RecyclerViewCalTrabajador>();
        listaEventosDias = new ArrayList<RecyclerViewCalTrabajador>();

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
                                listaEventos.add(new RecyclerViewCalTrabajador(fecha,document.get("Titulo"),
                                        document.get("Trabajador"),document.get("Adress")));
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

    }

    public static ArrayList<RecyclerViewCalTrabajador> getListaEventos() {
        return listaEventos;
    }

    public static void setListaEventos(ArrayList<RecyclerViewCalTrabajador> listaEventos) {
        EventosCalendario.listaEventos = listaEventos;
    }

    public static ArrayList<RecyclerViewCalTrabajador> getListaEventosDias() {
        return listaEventosDias;
    }

    public static void setListaEventosDias(ArrayList<RecyclerViewCalTrabajador> listaEventos, Date fecha) {

        String f = fecha.getDay() + "/" + fecha.getMonth() + "/" + fecha.getYear();

        if (listaEventos.size() > 0){
            getListaEventosDias().clear();
            for(int pos = 0; pos < listaEventos.size(); pos ++){
                if (listaEventos.get(pos).getSt_fecha_trabajo().equals(f)){
                    listaEventosDias.add(listaEventos.get(pos));
                }
            }
        }

}

}
