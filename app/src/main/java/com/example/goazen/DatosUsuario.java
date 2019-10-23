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
    private static String Sueldo;
    private static Boolean Limpieza_General, Limpieza_Cristales, Paseo_Mascotas, Plancha, Regado_Plantas, Lavanderia, Cocina;
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
        if (usu_tipo.equals("Trabajador")){
            datos.put("Sueldo", Sueldo);
            datos.put("Limpieza_General", Limpieza_General);
            datos.put("Limpieza_Cristales", Limpieza_Cristales);
            datos.put("Plancha", Plancha);
            datos.put("Cocina", Cocina);
            datos.put("Regado_Plantas", Regado_Plantas);
            datos.put("Paseo_Mascotas", Paseo_Mascotas);
            datos.put("Lavanderia", Lavanderia);
        }

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

    public static String getSueldo() {
        return Sueldo;
    }

    public static void setSueldo(String sueldo) {
        Sueldo = sueldo;
    }

    public static Boolean getLimpieza_General() {
        return Limpieza_General;
    }

    public static void setLimpieza_General(Boolean limpieza_General) {
        Limpieza_General = limpieza_General;
    }

    public static Boolean getLimpieza_Cristales() {
        return Limpieza_Cristales;
    }

    public static void setLimpieza_Cristales(Boolean limpieza_Cristales) {
        Limpieza_Cristales = limpieza_Cristales;
    }

    public static Boolean getPaseo_Mascotas() {
        return Paseo_Mascotas;
    }

    public static void setPaseo_Mascotas(Boolean paseo_Mascotas) {
        Paseo_Mascotas = paseo_Mascotas;
    }

    public static Boolean getPlancha() {
        return Plancha;
    }

    public static void setPlancha(Boolean plancha) {
        Plancha = plancha;
    }

    public static Boolean getRegado_Plantas() {
        return Regado_Plantas;
    }

    public static void setRegado_Plantas(Boolean regado_Plantas) {
        Regado_Plantas = regado_Plantas;
    }

    public static Boolean getLavanderia() {
        return Lavanderia;
    }

    public static void setLavanderia(Boolean lavanderia) {
        Lavanderia = lavanderia;
    }

    public static Boolean getCocina() {
        return Cocina;
    }

    public static void setCocina(Boolean cocina) {
        Cocina = cocina;
    }
}
