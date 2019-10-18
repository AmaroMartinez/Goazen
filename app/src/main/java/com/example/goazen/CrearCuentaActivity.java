package com.example.goazen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class CrearCuentaActivity extends AppCompatActivity {

    private Button btnCrearCuenta;
    private TextView textViewCampoVacio;
    private EditText editTextCCNombre, editTextCCApellido, editTextCCDNI, editTextCCEmail,
            editTextCCTelefono, editTextCCFNacimiento, editTextCCDireccion, editTextCCContraseña, editTextCCConfirmarContra;
    private static FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);


        btnCrearCuenta = findViewById(R.id.buttonCC);
        textViewCampoVacio = findViewById(R.id.textViewCampoVacio);
        editTextCCNombre = findViewById(R.id.editTextCCNombre);
        editTextCCApellido = findViewById(R.id.editTextCCApellido);
        editTextCCDNI = findViewById(R.id.editTextCCDNI);
        editTextCCEmail = findViewById(R.id.editTextCCEmail);
        editTextCCTelefono = findViewById(R.id.editTextCCTelefono);
        editTextCCFNacimiento = findViewById(R.id.editTextCCFNacimiento);
        editTextCCDireccion = findViewById(R.id.editTextCCDireccion);
        editTextCCContraseña = findViewById(R.id.editTextCCContraseña);
        editTextCCConfirmarContra = findViewById(R.id.editTextCCConfirmarContra);

        //Llama a la clase DateTextWatcher y le da el formato de fecha dd/mm/yyyy
        editTextCCFNacimiento.addTextChangedListener(new DateTextWatcher());


        btnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Si hay algun campo vacio
                if (editTextCCNombre.getText().toString().equals("") || editTextCCApellido.getText().toString().equals("")
                        || editTextCCDNI.getText().toString().equals("") || editTextCCEmail.getText().toString().equals("")
                        || editTextCCTelefono.getText().toString().equals("") || editTextCCFNacimiento.getText().toString().equals("") ||
                        editTextCCDireccion.getText().toString().equals("") || editTextCCConfirmarContra.getText().toString().equals("") ||
                        editTextCCConfirmarContra.getText().toString().equals("")) {
                    textViewCampoVacio.setVisibility(View.VISIBLE);

                } else {

                    VerificarEmail();
                    InsertarCuenta();
                    VerificarContraseña();

                    //Vuelve a la ventana de login
                    Intent myIntent = new Intent(CrearCuentaActivity.this, LoginActivity.class);
                    startActivity(myIntent);

                    /*
                    String emailAVerificar = editTextCCEmail.getText().toString();
                    String requisitos =
                            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"

                                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"

                                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."

                                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"

                                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"

                                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";


                    if (emailAVerificar.matches(requisitos) && emailAVerificar.length() > 0) {
                        // Si la contraseñas no coinciden
                        if (!editTextCCContraseña.getText().toString().equals(editTextCCConfirmarContra.getText().toString())) {
                            textViewCampoVacio.setText("Las contraseñas no coinciden");
                            textViewCampoVacio.setVisibility(View.VISIBLE);

                        }
                        else {

                            //Inserta los datos en la base de datos
                            db = FirebaseFirestore.getInstance();
                            CollectionReference Usuarios = db.collection("Usuarios");

                            Map<String, Object> datos = new HashMap<>();
                            datos.put("Nombre", editTextCCNombre.getText().toString());
                            datos.put("Apellido", editTextCCApellido.getText().toString());
                            datos.put("DNI", editTextCCDNI.getText().toString());
                            datos.put("Adress", editTextCCDireccion.getText().toString());
                            datos.put("fnacimiento", editTextCCFNacimiento.getText().toString());
                            datos.put("movil", editTextCCDireccion.getText().toString());
                            datos.put("email", editTextCCEmail.getText().toString());
                            datos.put("Contrasena", editTextCCContraseña.getText().toString());
                            datos.put("usu_tipo", "Cliente");

                            Usuarios.document(editTextCCDNI.getText().toString()).set(datos);

                            //Vuelve a la ventana de login
                            Intent myIntent = new Intent(CrearCuentaActivity.this, LoginActivity.class);
                            startActivity(myIntent);

                        }
                    } else {
                        textViewCampoVacio.setVisibility(View.VISIBLE);
                        textViewCampoVacio.setText("Email invalido");

                    }

                    */
                }


            }


        });

    }

    private void VerificarEmail() {
        String emailAVerificar = editTextCCEmail.getText().toString();
        String requisitos =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"

                        + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"

                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."

                        + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"

                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"

                        + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";


        if (emailAVerificar.matches(requisitos) && emailAVerificar.length() > 0) {



        }
        else {
            textViewCampoVacio.setVisibility(View.VISIBLE);
            textViewCampoVacio.setText("Email invalido");

        }


    }

    private void InsertarCuenta(){

        //Inserta los datos en la base de datos
        db = FirebaseFirestore.getInstance();
        CollectionReference Usuarios = db.collection("Usuarios");

        Map<String, Object> datos = new HashMap<>();
        datos.put("Nombre", editTextCCNombre.getText().toString());
        datos.put("Apellido", editTextCCApellido.getText().toString());
        datos.put("DNI", editTextCCDNI.getText().toString());
        datos.put("Adress", editTextCCDireccion.getText().toString());
        datos.put("fnacimiento", editTextCCFNacimiento.getText().toString());
        datos.put("movil", editTextCCDireccion.getText().toString());
        datos.put("email", editTextCCEmail.getText().toString());
        datos.put("Contrasena", editTextCCContraseña.getText().toString());
        datos.put("usu_tipo", "Cliente");

        Usuarios.document(editTextCCDNI.getText().toString()).set(datos);
    }

    private void VerificarContraseña(){

        if (!editTextCCContraseña.getText().toString().equals(editTextCCConfirmarContra.getText().toString())) {
            textViewCampoVacio.setText("Las contraseñas no coinciden");
            textViewCampoVacio.setVisibility(View.VISIBLE);

        }
        else{

        }
    }


}
