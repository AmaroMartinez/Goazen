package com.example.goazen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

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



        btnCrearCuenta= findViewById(R.id.buttonCC);
        textViewCampoVacio=findViewById(R.id.textViewCampoVacio);
        editTextCCNombre=findViewById(R.id.editTextCCNombre);
        editTextCCApellido=findViewById(R.id.editTextCCApellido);
        editTextCCDNI=findViewById(R.id.editTextCCDNI);
        editTextCCEmail=findViewById(R.id.editTextCCEmail);
        editTextCCTelefono=findViewById(R.id.editTextCCTelefono);
        editTextCCFNacimiento=findViewById(R.id.editTextCCFNacimiento);
        editTextCCDireccion=findViewById(R.id.editTextCCDireccion);
        editTextCCContraseña=findViewById(R.id.editTextCCContraseña);
        editTextCCConfirmarContra=findViewById(R.id.editTextCCConfirmarContra);


        btnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Si hay algun campo vacio
                if(editTextCCNombre.getText().toString().equals("")||editTextCCApellido.getText().toString().equals("")
                ||editTextCCDNI.getText().toString().equals("")||editTextCCEmail.getText().toString().equals("")
                        ||editTextCCTelefono.getText().toString().equals("")||editTextCCFNacimiento.getText().toString().equals("")||
                        editTextCCDireccion.getText().toString().equals("")||editTextCCConfirmarContra.getText().toString().equals("")||
                        editTextCCConfirmarContra.getText().toString().equals("")){
                    textViewCampoVacio.setVisibility(View.VISIBLE);

                }

                else{
                    
                    // Si la contraseñas no coinciden
                    if(!editTextCCContraseña.getText().toString().equals(editTextCCConfirmarContra.getText().toString())){
                        textViewCampoVacio.setText("Las contraseñas no coinciden");
                        textViewCampoVacio.setVisibility(View.VISIBLE);

                    }
                    else{

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

                }


            }
        });

    }
}
