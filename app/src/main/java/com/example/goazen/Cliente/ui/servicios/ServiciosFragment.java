package com.example.goazen.Cliente.ui.servicios;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.goazen.Cliente.PopUpPagos;
import com.example.goazen.R;
import com.example.goazen.Servicios;
import com.example.goazen.Values;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ServiciosFragment extends Fragment {

    private Button btnlimpiezageneral;
    private Button btnlimpiezacristales;
    private Button btnplancha;
    private Button btncocina;
    private Button btnlavanderia;
    private Button btnregadoplantas;
    private Button btnpaseomascotas;
    private Button btncontratar;
    private Button btncancelar;
    private ServiciosViewModel serviciosViewModel;

    //Declaramos la conexión a la base de datos
    private static FirebaseFirestore db;

    //Campos a descargar de la base de datos;
    private ArrayList<Servicios> ListaServicios;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        serviciosViewModel =
                ViewModelProviders.of(this).get(ServiciosViewModel.class);
        View root = inflater.inflate(R.layout.fragment_servicios, container, false);

        // Relacionar los botones con su id
        btnlimpiezageneral = root.findViewById(R.id.btn_limpieza_general);
        btnlimpiezacristales = root.findViewById(R.id.btn_limpieza_cristales);
        btnplancha = root.findViewById(R.id.btn_plancha);
        btncocina = root.findViewById(R.id.btn_cocina);
        btnlavanderia = root.findViewById(R.id.btn_lavanderia);
        btnregadoplantas = root.findViewById(R.id.btn_regado_plantas);
        btnpaseomascotas = root.findViewById(R.id.btn_paseo_mascotas);
        btncontratar = root.findViewById(R.id.btn_contratar);
        btncancelar = root.findViewById(R.id.btn_cancelar);
        ConfigurarPantalla();

        // Accion del Boton Limpieza General
        btnlimpiezageneral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), Calendario.class);
                myIntent.putExtra("servicio", btnlimpiezageneral.getText());
                startActivity(myIntent);
            }
        });

        // Accion del Boton Limpieza Cristales
        btnlimpiezacristales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), Calendario.class);
                myIntent.putExtra("servicio", btnlimpiezacristales.getText());
                startActivity(myIntent);
            }
        });

        // Accion del Boton Cocina
        btncocina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), Calendario.class);
                myIntent.putExtra("servicio", btncocina.getText());
                startActivity(myIntent);
            }
        });

        // Accion del Boton Plancha
        btnplancha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), Calendario.class);
                myIntent.putExtra("servicio", btnplancha.getText());
                startActivity(myIntent);
            }
        });

        // Accion del Boton Lavanderia
        btnlavanderia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), Calendario.class);
                myIntent.putExtra("servicio", btnlavanderia.getText());
                startActivity(myIntent);
            }
        });

        // Accion del Boton Regado de Plantas
        btnregadoplantas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), Calendario.class);
                myIntent.putExtra("servicio", btnregadoplantas.getText());
                startActivity(myIntent);
            }
        });

        // Accion del Boton Paseo de Mascotas
        btnpaseomascotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), Calendario.class);
                myIntent.putExtra("servicio", btnpaseomascotas.toString());
                startActivity(myIntent);
            }
        });

        //Accion del Boton contratar
        btncontratar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent popUp = new Intent(getContext(), PopUpPagos.class);
                startActivity(popUp);
            }
        });

        serviciosViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });

        return root;
    }

    public void ConfigurarPantalla () {
        Log.d(Values.tag_log, "entramos en configurarPantalla");
        ListaServicios = new ArrayList<Servicios>();
        db = FirebaseFirestore.getInstance();
        db.collection("Servicios")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                /*Cogemos los datos de cada uno de los servicios y los introducimo
                                en el array*/
                                ListaServicios.add(new Servicios(document.getId(), document.getBoolean("Enable"), document.getDouble("Precio")));
                                Log.d(Values.tag_log, String.valueOf(ListaServicios.size()));
                            }
                        }
                        if (task.isComplete()) {
                            for (int pos = 0; pos < ListaServicios.size(); pos++) {
                                switch (ListaServicios.get(pos).getNombre_servicio()) {
                                    case Values.SERV_GENERAL:
                                        btnlimpiezageneral.setEnabled(ListaServicios.get(pos).getEnable());
                                        if (!btnlimpiezageneral.isEnabled()){
                                            btnlimpiezageneral.setBackgroundColor(R.color.color_botones_servicio_no_contratables);
                                        }
                                        break;
                                    case Values.SERV_CRISTALES:
                                        btnlimpiezacristales.setEnabled(ListaServicios.get(pos).getEnable());
                                        if (!btnlimpiezacristales.isEnabled()){
                                            btnlimpiezacristales.setBackgroundColor(R.color.color_botones_servicio_no_contratables);
                                        }
                                        break;
                                    case Values.SERV_COCINA:
                                        btncocina.setEnabled(ListaServicios.get(pos).getEnable());
                                        if (!btncocina.isEnabled()){
                                            btncocina.setBackgroundColor(R.color.color_botones_servicio_no_contratables);
                                        }
                                        break;
                                    case Values.SERV_LAVANDERIA:
                                        btnlavanderia.setEnabled(ListaServicios.get(pos).getEnable());
                                        if (!btnlavanderia.isEnabled()){
                                            btnlavanderia.setBackgroundColor(R.color.color_botones_servicio_no_contratables);
                                        }
                                        break;
                                    case Values.SERV_PASEO:
                                        btnpaseomascotas.setEnabled(ListaServicios.get(pos).getEnable());
                                        if (!btnpaseomascotas.isEnabled()){
                                            btnpaseomascotas.setBackgroundColor(R.color.color_botones_servicio_no_contratables);
                                        }
                                        break;
                                    case Values.SERV_PLANCHA:
                                        btnplancha.setEnabled(ListaServicios.get(pos).getEnable());
                                        if (!btnplancha.isEnabled()){
                                            btnplancha.setBackgroundColor(R.color.color_botones_servicio_no_contratables);
                                        }
                                        break;
                                    case Values.SERV_REGADO:
                                        btnregadoplantas.setEnabled(ListaServicios.get(pos).getEnable());
                                        if (!btnregadoplantas.isEnabled()){
                                            btnregadoplantas.setBackgroundColor(R.color.color_botones_servicio_no_contratables);
                                        }
                                        break;
                                    default:
                                        break;
                                }
                            }

                        } else {
                            Log.d(Values.tag_log, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
}