package com.example.goazen;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class DatosCliente extends Application {
    private static String Nombre;
    private static String Apellido;
    private static String DNI;
    private static String movil;
    private static String Contrasena;
    private static String email;
    private static String c_bancaria;
    private static String Adress;
    private static String usu_tipo;
    private static String fnacimiento;
    private static String ID;
    private static FirebaseFirestore db;

    @Override
    public void onCreate(){
        super.onCreate();
        db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("Usuarios").document("usu1");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());

                        Nombre = document.getString("Nombre");
                        Apellido = document.getString("Apellido");
                        Adress = document.getString("Adress");
                        DNI = document.getString("DNI");
                        email = document.getString("email");
                        fnacimiento = document.getString("fnacimiento");
                        movil = document.getString("movil");
                        Contrasena = document.getString("Contrasena");
                        c_bancaria = document.getString("c_bancaria");
                        usu_tipo = document.getString("usu_tipo");
                        ID = "usu1";

                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }

    public static void writeUsuario(){
        db = FirebaseFirestore.getInstance();
        CollectionReference Usuarios = db.collection("Usuarios");

        Map<String, Object> datos = new HashMap<>();
        datos.put("Nombre", Nombre);
        datos.put("Apellido", Apellido);
        datos.put("DNI", DNI);
        datos.put("Adress", Adress);
        datos.put("fnacimiento", fnacimiento);
        datos.put("movil", movil);
        datos.put("email", email);
        datos.put("Contrasena", Contrasena);
        datos.put("usu_tipo", usu_tipo);
        datos.put("c_bancaria", c_bancaria);

        //data1.put("regions", Arrays.asList("west_coast", "norcal"));
        Usuarios.document("usu1").set(datos);
    }

    @Override
    public void onLowMemory(){
        super.onLowMemory();
    }

    public static String getNombre() {
        return Nombre;
    }

    public static void setNombre(String nombre) {
        Nombre = nombre;
    }

    public static String getApellido() {
        return Apellido;
    }

    public static void setApellido(String apellido) {
        Apellido = apellido;
    }

    public static String getDNI() {
        return DNI;
    }

    public static void setDNI(String DNI) {
        DatosCliente.DNI = DNI;
    }

    public static String getMovil() {
        return movil;
    }

    public static void setMovil(String movil) {
        DatosCliente.movil = movil;
    }

    public static String getContrasena() {
        return Contrasena;
    }

    public static void setContrasena(String contrasena) {
        DatosCliente.Contrasena = contrasena;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        DatosCliente.email = email;
    }

    public static String getC_bancaria() {
        return c_bancaria;
    }

    public static void setC_bancaria(String c_bancaria) {
        DatosCliente.c_bancaria = c_bancaria;
    }

    public static String getAdress() {
        return Adress;
    }

    public static void setAdress(String adress) {
        Adress = adress;
    }

    public static String getUsu_tipo() {
        return usu_tipo;
    }

    public static void setUsu_tipo(String usu_tipo) {
        DatosCliente.usu_tipo = usu_tipo;
    }

    public static String getFnacimiento() {
        return fnacimiento;
    }

    public static void setFnacimiento(String fnacimiento) {
        DatosCliente.fnacimiento = fnacimiento;
    }

    public static String getID() {
        return ID;
    }

    public static void setID(String ID) {
        DatosCliente.ID = ID;
    }
}
