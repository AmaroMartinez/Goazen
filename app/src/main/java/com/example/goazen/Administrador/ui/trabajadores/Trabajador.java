package com.example.goazen.Administrador.ui.trabajadores;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class Trabajador {

    private  String Nombre;
    private  String Dni;
    private static ArrayList<Trabajador> trabajadores = new ArrayList();

    private static FirebaseFirestore db;

    public static void leertrabajadores() {

        //trabajadores = new ArrayList();
        db = FirebaseFirestore.getInstance();

        db.collection("Usuarios")
                .whereEqualTo("usu_tipo", "Trabajador")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            trabajadores.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                Trabajador trabajador = new Trabajador((String) document.get("Nombre"), (String) document.get("DNI"));
                                trabajadores.add(trabajador);
                                Log.d(TAG, String.valueOf(trabajadores));
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    public Trabajador(String nombre, String Dni){
        this.Nombre=nombre;
        this.Dni=Dni;
    }

    public  String getNombre() {
        return Nombre;
    }

    public  void setNombre(String nombre) {
        Nombre = nombre;
    }

    public  String getDni() {
        return Dni;
    }

    public  void setDni(String dni) {
        Dni = dni;
    }


    public static ArrayList<Trabajador> getTrabajadores() {
        Log.d("tag", "tamaño arraylist trabajadores: " + trabajadores.size());
        return trabajadores;
    }

}
