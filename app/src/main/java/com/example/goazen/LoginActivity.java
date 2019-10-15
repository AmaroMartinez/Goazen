package com.example.goazen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.goazen.Cliente.MainActivity;

public class LoginActivity extends AppCompatActivity {

    Spinner desplegableusuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        desplegableusuarios = (Spinner) findViewById(R.id.desplegable_usuarios);

        String[] usuarios = {"Selecciona un usuario","Cliente","Trabajador","Administrador"};
        desplegableusuarios.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, usuarios));

        desplegableusuarios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (desplegableusuarios.getSelectedItem().toString().equals("Cliente")){
                    Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(myIntent);

                };
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
