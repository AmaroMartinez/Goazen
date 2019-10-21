package com.example.goazen.Administrador.ui.trabajadores;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.goazen.DateTextWatcher;
import com.example.goazen.LoginActivity;
import com.example.goazen.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CrearCuentaTrabajadorActivity extends AppCompatActivity {

    private Button btnCrearCuentaTrabajador;
    private TextView textViewCampoVacio;
    private EditText editTextCTNombre, editTextCTApellido, editTextCTDNI, editTextCTEmail,
            editTextCTTelefono, editTextCTFNacimiento, editTextCTDireccion, editTextCTContraseña, editTextCTConfirmarContra, editTextCTSueldo;
    private static FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);


        btnCrearCuentaTrabajador = findViewById(R.id.buttonCT);
        textViewCampoVacio = findViewById(R.id.textViewCampoVacioTrabajador);
        editTextCTNombre = findViewById(R.id.editTextCTNombre);
        editTextCTApellido = findViewById(R.id.editTextCTApellido);
        editTextCTDNI = findViewById(R.id.editTextCTDNI);
        editTextCTEmail = findViewById(R.id.editTextCTEmail);
        editTextCTTelefono = findViewById(R.id.editTextCTTelefono);
        editTextCTFNacimiento = findViewById(R.id.editTextCTFNacimiento);
        editTextCTDireccion = findViewById(R.id.editTextCTDireccion);
        editTextCTContraseña = findViewById(R.id.editTextCTContraseña);
        editTextCTConfirmarContra = findViewById(R.id.editTextCTConfirmarContra);
        editTextCTSueldo = findViewById(R.id.editTextCTSueldo);

        //Llama a la clase DateTextWatcher y le da el formato de fecha dd/mm/yyyy
        editTextCTFNacimiento.addTextChangedListener(new DateTextWatcher());


        btnCrearCuentaTrabajador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Verifica todos los datos, va llamando a todos los metodos de verificación.
                VerificarDatos();



            }

        });

    }

    private void VerificarDatos(){

        if (editTextCTNombre.getText().toString().equals("") || editTextCTApellido.getText().toString().equals("")
                || editTextCTDNI.getText().toString().equals("") || editTextCTEmail.getText().toString().equals("")
                || editTextCTTelefono.getText().toString().equals("") || editTextCTFNacimiento.getText().toString().equals("") ||
                editTextCTDireccion.getText().toString().equals("") || editTextCTConfirmarContra.getText().toString().equals("") ||
                editTextCTConfirmarContra.getText().toString().equals("")) {

            textViewCampoVacio.setVisibility(View.VISIBLE);
        }

        else{
            VerificarDNI();
        }


    }


    private void VerificarEmail() {
        String emailAVerificar = editTextCTEmail.getText().toString();
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
        String fechaAVerificar = editTextCTFNacimiento.getText().toString();
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
        String DNIAVerificar = editTextCTDNI.getText().toString();
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

        if(editTextCTTelefono.length()<9){

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
        datos.put("Nombre", editTextCTNombre.getText().toString());
        datos.put("Apellido", editTextCTApellido.getText().toString());
        datos.put("DNI", editTextCTDNI.getText().toString());
        datos.put("Adress", editTextCTDireccion.getText().toString());
        datos.put("fnacimiento", editTextCTFNacimiento.getText().toString());
        datos.put("movil", editTextCTDireccion.getText().toString());
        datos.put("email", editTextCTEmail.getText().toString());
        datos.put("Contrasena", editTextCTContraseña.getText().toString());
        datos.put("Sueldo" , editTextCTSueldo.getText());
        datos.put("usu_tipo", "Trabajador");

        Usuarios.document(editTextCTDNI.getText().toString()).set(datos);


        //Vuelve a la ventana de login
        Intent myIntent = new Intent(CrearCuentaTrabajadorActivity.this, LoginActivity.class);
        startActivity(myIntent);


    }

    private void VerificarContraseña(){

        if (!editTextCTContraseña.getText().toString().equals(editTextCTConfirmarContra.getText().toString())) {
            textViewCampoVacio.setText("Las contraseñas no coinciden");
            textViewCampoVacio.setVisibility(View.VISIBLE);

        }
        else{
            InsertarCuenta();

        }
    }


}
