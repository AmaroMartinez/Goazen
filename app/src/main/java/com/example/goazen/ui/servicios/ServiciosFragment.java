package com.example.goazen.ui.servicios;

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
        serviciosViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });
        return root;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        btnlimpiezageneral.findViewById(R.id.btn_limpieza_general);
        btnlimpiezacristales.findViewById(R.id.btn_limpieza_cristales);
        btncocina.findViewById(R.id.btn_cocina);
        btnplancha.findViewById(R.id.btn_plancha);
        btnpaseomascotas.findViewById(R.id.btn_paseo_mascotas);
        btnlavanderia.findViewById(R.id.btn_lavanderia);
        btnregadoplantas.findViewById(R.id.btn_regado_plantas);

        btnregadoplantas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), Calendario.class);
                myIntent.putExtra("servicio", btnregadoplantas.getText());
                getActivity().startActivity(myIntent);

            }
        });

    }
}