package com.example.goazen;

import android.app.Application;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DatosUsuario extends Application {

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
        /*db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("Usuarios").document("usu4");
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
                        ID = DNI;

                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });*/
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
        Usuarios.document(DNI).set(datos);
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
        DatosUsuario.DNI = DNI;
    }

    public static String getMovil() {
        return movil;
    }

    public static void setMovil(String movil) {
        DatosUsuario.movil = movil;
    }

    public static String getContrasena() {
        return Contrasena;
    }

    public static void setContrasena(String contrasena) {
        DatosUsuario.Contrasena = contrasena;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        DatosUsuario.email = email;
    }

    public static String getC_bancaria() {
        return c_bancaria;
    }

    public static void setC_bancaria(String c_bancaria) {
        DatosUsuario.c_bancaria = c_bancaria;
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
        DatosUsuario.usu_tipo = usu_tipo;
    }

    public static String getFnacimiento() {
        return fnacimiento;
    }

    public static void setFnacimiento(String fnacimiento) {
        DatosUsuario.fnacimiento = fnacimiento;
    }

    public static String getID() {
        return ID;
    }

    public static void setID(String ID) {
        DatosUsuario.ID = ID;
    }
}
