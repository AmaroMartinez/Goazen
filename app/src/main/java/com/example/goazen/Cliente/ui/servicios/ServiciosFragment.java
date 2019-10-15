package com.example.goazen.Cliente.ui.servicios;

import android.content.Intent;
import android.os.Bundle;
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

}