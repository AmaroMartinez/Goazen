package com.example.goazen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.goazen.Administrador.MainActivityAdmin;
import com.example.goazen.Cliente.MainActivity;

public class LoginActivity extends AppCompatActivity {

    // Desplegable
    Spinner desplegableusuarios;
    private Button btnLoginEntrar;
    private EditText editTextLoginUsuario, editTextLoginContraseña;

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



                if(editTextLoginUsuario.getText().toString().equals("cliente")&&editTextLoginContraseña.getText().toString().equals("cliente")){

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
