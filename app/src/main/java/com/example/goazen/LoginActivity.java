package com.example.goazen;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.goazen.Administrador.MainActivityAdmin;
import com.example.goazen.Cliente.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import static android.content.ContentValues.TAG;

public class LoginActivity extends AppCompatActivity {

    // Desplegable
    Spinner desplegableusuarios;
    private Button btnLoginEntrar;
    private EditText editTextLoginUsuario, editTextLoginContraseña;
    private static FirebaseFirestore db;
    private static String DNI;
    private static String Contrasena;

    //Metodo onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        desplegableusuarios = findViewById(R.id.desplegable_usuarios);

        btnLoginEntrar= findViewById(R.id.buttonLoginEntrar);
        editTextLoginUsuario= findViewById(R.id.editTextLoginUsuario);
        editTextLoginContraseña= findViewById(R.id.editTextLoginContraseña);

        btnLoginEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                db = FirebaseFirestore.getInstance();
                DocumentReference docRef = db.collection("Usuarios").document(editTextLoginUsuario.getText().toString());
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Log.d(TAG, "DocumentSnapshot data: " + document.getData());

                                DNI = document.getString("DNI");
                                Contrasena = document.getString("Contrasena");

                            } else {
                                Log.d(TAG, "No such document");
                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });





                if(editTextLoginUsuario.getText().toString().equals(DNI)&&editTextLoginContraseña.getText().toString().equals(Contrasena)){

                    Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(myIntent);
                }

            }
        });



        String[] usuarios = {"Selecciona un usuario","Cliente","Trabajador","Administrador"};
        desplegableusuarios.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, usuarios));

        desplegableusuarios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (desplegableusuarios.getSelectedItem().toString().equals("Cliente")){
                    Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(myIntent);

                } else if (desplegableusuarios.getSelectedItem().toString().equals("Administrador")){
                    Intent myIntent = new Intent(LoginActivity.this, MainActivityAdmin.class);
                    startActivity(myIntent);

                };
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    // Asignar la funcion de minimizar la aplicacion con el boton atras del telefono
    @Override public void onBackPressed() { moveTaskToBack(true); }
}
