package com.example.goazen;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    Spinner desplegableusuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        desplegableusuarios = (Spinner) findViewById(R.id.desplegable_usuarios);

        String[] usuarios = {"Cliente","Trabajador","Administrador"};
        desplegableusuarios.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, usuarios));

    }
}
