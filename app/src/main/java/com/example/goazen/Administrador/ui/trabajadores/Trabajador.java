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

    private static String Nombre;
    private static String Apellido;
    private static ArrayList<Trabajador> Trabajadores;

    private static FirebaseFirestore db;

    public static void leertrabajadores() {

        Trabajadores = new ArrayList<Trabajador>();
        db = FirebaseFirestore.getInstance();

        db.collection("Usuarios")
                .whereEqualTo("usu_tipo", "Trabajador")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                Trabajadores.add(new Trabajador(document.get("Nombre").toString(), document.get("Apellido").toString()));
                                Log.d(TAG, String.valueOf(Trabajadores));
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    public Trabajador(String nombre, String apellido){
        this.Nombre=nombre;
        this.Apellido=apellido;
    }

}
