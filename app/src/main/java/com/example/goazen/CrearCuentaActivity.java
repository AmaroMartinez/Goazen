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
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

                //Verifica todos los datos, va llamando a todos los metodos de verificación.
                VerificarDatos();



            }

        });

    }

    private void VerificarDatos(){

        if (editTextCCNombre.getText().toString().equals("") || editTextCCApellido.getText().toString().equals("")
                || editTextCCDNI.getText().toString().equals("") || editTextCCEmail.getText().toString().equals("")
                || editTextCCTelefono.getText().toString().equals("") || editTextCCFNacimiento.getText().toString().equals("") ||
                editTextCCDireccion.getText().toString().equals("") || editTextCCConfirmarContra.getText().toString().equals("") ||
                editTextCCConfirmarContra.getText().toString().equals("")) {

            textViewCampoVacio.setVisibility(View.VISIBLE);
        }

        else{
            VerificarDNI();
        }


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

            VerificarNumeroTelefono();

        }
        else {
            textViewCampoVacio.setVisibility(View.VISIBLE);
            textViewCampoVacio.setText("Email invalido");

        }


    }

    private void Verificarfecha() {
        String fechaAVerificar = editTextCCFNacimiento.getText().toString();
        String requisitos =
                "^(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\\d{2}$";


        if (fechaAVerificar.matches(requisitos) && fechaAVerificar.length() > 0) {

           VerificarContraseña();

        }
        else {
            textViewCampoVacio.setVisibility(View.VISIBLE);
            textViewCampoVacio.setText("Fecha invalida");

        }


    }


    private void VerificarDNI() {
        String DNIAVerificar = editTextCCDNI.getText().toString();
        String requisitos =
                "(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])";


        if (DNIAVerificar.matches(requisitos) && DNIAVerificar.length() > 0) {

           VerificarEmail();

        }
        else {
            textViewCampoVacio.setVisibility(View.VISIBLE);
            textViewCampoVacio.setText(" DNI invalido");

        }


    }


    private void VerificarNumeroTelefono(){

        if(editTextCCTelefono.length()<9){

            textViewCampoVacio.setText("Numero de teléfono invalido");
            textViewCampoVacio.setVisibility(View.VISIBLE);

        }
        else{
            Verificarfecha();
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


        //Vuelve a la ventana de login
        Intent myIntent = new Intent(CrearCuentaActivity.this, LoginActivity.class);
        startActivity(myIntent);


    }

    private void VerificarContraseña(){

        if (!editTextCCContraseña.getText().toString().equals(editTextCCConfirmarContra.getText().toString())) {
            textViewCampoVacio.setText("Las contraseñas no coinciden");
            textViewCampoVacio.setVisibility(View.VISIBLE);

        }
        else{
            InsertarCuenta();

        }
    }


}
