package com.example.goazen;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.goazen.Administrador.MainActivityAdmin;
import com.example.goazen.Cliente.MainActivity;
import com.example.goazen.RecuperarContraseña.RecuperarContrasena;
import com.example.goazen.Trabajador.MainActivity_Trabajador;
import com.example.goazen.Trabajador.ui.calendario.EventosCalendario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {

    // Desplegable
    private Button btnLoginEntrar;
    private Button btnLoginCrearCuenta;
    private EditText editTextLoginUsuario;
    private EditText editTextLoginContraseña;
    private CheckBox vercontrasena;
    private TextView tvContraseñaOlvidada;
    private TextView tvDatosErroneos;
    private static FirebaseFirestore db;
    private static String DNI;
    private static String Contrasena;

    //Metodo onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLoginCrearCuenta= findViewById(R.id.buttonLoginCrearCuenta);
        btnLoginEntrar= findViewById(R.id.buttonLoginEntrar);
        editTextLoginUsuario= findViewById(R.id.editTextLoginUsuario);
        editTextLoginContraseña= findViewById(R.id.editTextLoginContraseña);
        vercontrasena = findViewById(R.id.ch_ver_contrasena);
        tvContraseñaOlvidada= findViewById(R.id.textViewContraseñaOlvidada);
        tvDatosErroneos=findViewById(R.id.textViewDatosErroneos);

        tvContraseñaOlvidada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Te lleva al activity para recuperar la contraseña
                Intent myIntent = new Intent(LoginActivity.this, RecuperarContrasena.class);
                startActivity(myIntent);
            }
        });

        btnLoginEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Carga los datos
                db = FirebaseFirestore.getInstance();
                DocumentReference docRef = db.collection("Usuarios").document(editTextLoginUsuario.getText().toString());
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Log.d(Values.tag_log, "DocumentSnapshot data: " + document.getData());
                                DNI = document.getString("DNI");
                                Contrasena = document.getString("Contrasena");
                                DatosUsuario.setNombre(document.getString("Nombre"));
                                DatosUsuario.setApellido(document.getString("Apellido"));
                                DatosUsuario.setDNI(document.getString("DNI"));
                                DatosUsuario.setAdress(document.getString("Adress"));
                                DatosUsuario.setEmail(document.getString("email"));
                                DatosUsuario.setFnacimiento(document.getString("fnacimiento"));
                                DatosUsuario.setMovil(document.getString("movil"));
                                DatosUsuario.setContrasena(document.getString("Contrasena"));
                                DatosUsuario.setC_bancaria(document.getString("c_bancaria"));
                                DatosUsuario.setUsu_tipo(document.getString("usu_tipo"));
                                if (DatosUsuario.getUsu_tipo().equals("Trabajador")){
                                    DatosUsuario.setSueldo(document.getString("Sueldo"));
                                    DatosUsuario.setLimpieza_General(document.getBoolean("Limpieza_General"));
                                    DatosUsuario.setLimpieza_Cristales(document.getBoolean("Limpieza_Cristales"));
                                    DatosUsuario.setPlancha(document.getBoolean("Plancha"));
                                    DatosUsuario.setCocina(document.getBoolean("Cocina"));
                                    DatosUsuario.setRegado_Plantas(document.getBoolean("Regado_Plantas"));
                                    DatosUsuario.setPaseo_Mascotas(document.getBoolean("Paseo_Mascotas"));
                                    DatosUsuario.setLavanderia(document.getBoolean("Lavanderia"));
                                    DatosUsuario.setKm(document.getString("Km"));
                                    DatosUsuario.setAntiguedad(document.getString("Antiguedad"));
                                }
                                DatosUsuario.setID(editTextLoginUsuario.getText().toString());

                                //Lee los eventos de los trabajadores
                                EventosCalendario.readEventos();

                                //Se comprueba que tipo de usuario ha hecho login y se se abre su correspondiente pantalla
                                if(editTextLoginUsuario.getText().toString().equals(DNI)&&editTextLoginContraseña.getText().toString().equals(Contrasena)){
                                    if (DatosUsuario.getUsu_tipo().equals("Cliente")) {
                                        Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(myIntent);
                                    } else if (DatosUsuario.getUsu_tipo().equals("Admin")) {
                                        Intent myIntent = new Intent(LoginActivity.this, MainActivityAdmin.class);
                                        startActivity(myIntent);
                                    } else if (DatosUsuario.getUsu_tipo().equals("Trabajador")) {
                                        Intent myIntent = new Intent(LoginActivity.this, MainActivity_Trabajador.class);
                                        startActivity(myIntent);
                                    }
                                }


                            } else {
                                //Si los datos introducidos son erroneos el textview se hace visible
                                Log.d(Values.tag_log, "No such document");
                                tvDatosErroneos.setVisibility(View.VISIBLE);
                                //System.out.println("Datos erroneos");

                            }
                        } else {
                            Log.d(Values.tag_log, "get failed with ", task.getException());
                        }
                    }
                });


            }
        });

        btnLoginCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Lleva a la pantalla de crear cuenta
                Intent myIntent = new Intent(LoginActivity.this, CrearCuentaActivity.class);
                startActivity(myIntent);
            }
        });

        //Permite ver la contraseña
        vercontrasena.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    editTextLoginContraseña.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                    editTextLoginContraseña.setInputType(129);
                }
            }
        });

    }


    // Asignar la funcion de minimizar la aplicacion con el boton atras del telefono
    @Override public void onBackPressed() { moveTaskToBack(true); }
}
