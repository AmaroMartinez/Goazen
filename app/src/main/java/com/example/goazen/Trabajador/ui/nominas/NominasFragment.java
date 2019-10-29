package com.example.goazen.Trabajador.ui.nominas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.goazen.R;
import com.google.firebase.firestore.FirebaseFirestore;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/*------------------------------------------------------------------------------------------------
 * EN ESTA CLASE SE VA A CONFIGURAR LA PANTALLA DE VISUALIZACIÓN DE LA ÚLTIMA NOMINA DEL TRABAJADOR
 * -------------------------------------- QUE SE HA LOGUEADO --------------------------------------
 * ------------------------------------------------------------------------------------------------*/

public class NominasFragment extends Fragment {

    private NominasViewModel nominaViewModel;
    private FirebaseFirestore db;
    private Button btnDescargar;
    private EditText domicilio;
    private EditText trabajador;
    private EditText dni;
    private EditText telefono;
    private EditText fecha_de_ingreso;
    private TextView deduccionSB;
    private TextView deduccionKM;
    private TextView deduccionANT;
    private EditText totalNomina;
    private TextView cuantiaKM;
    private TextView cuantiaAnt;
    private double porcentajekm = 0.08;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        nominaViewModel =
                ViewModelProviders.of(this).get(NominasViewModel.class);
        View root = inflater.inflate(R.layout.fragment_nominas, container, false);
        nominaViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });

        // Access a Cloud Firestore instance from your Activity
        db = FirebaseFirestore.getInstance();

        //Enlazamod los textos con nuestras variables
        domicilio = root.findViewById(R.id.et_direccion);
        trabajador = root.findViewById(R.id.et_nombre_apellido);
        dni = root.findViewById(R.id.et_dni);
        telefono = root.findViewById(R.id.et_telefono);
        fecha_de_ingreso = root.findViewById(R.id.et_fecha_ingreso);
        deduccionSB = root.findViewById(R.id.deduccionSB);
        deduccionKM = root.findViewById(R.id.deduccionKM);
        deduccionANT = root.findViewById(R.id.deduccionANT);
        totalNomina = root.findViewById(R.id.et_total);
        cuantiaAnt = root.findViewById(R.id.cuantiaAnt);
        cuantiaKM = root.findViewById(R.id.cuantiaKM);

        //Cargamos lo datos de la base de datos, para anexarlos a los textos.

        domicilio.setText(DatosUsuario.getAdress());

        String nombreyapellido = DatosUsuario.getNombre() + " " + DatosUsuario.getApellido();
        trabajador.setText(nombreyapellido);

        dni.setText(DatosUsuario.getDNI());
        telefono.setText(DatosUsuario.getMovil());
        fecha_de_ingreso.setText(DatosUsuario.getAntiguedad());
        deduccionSB.setText(DatosUsuario.getSueldo());
        cuantiaKM.setText(DatosUsuario.getKm());

        Double km = Double.parseDouble(DatosUsuario.getKm());
        km = km * porcentajekm;
        String calculokm = km.toString();
        deduccionKM.setText(km.toString());

        String antiguedad = DatosUsuario.getAntiguedad();
        String año = String.valueOf(antiguedad.charAt(6))+ String.valueOf(antiguedad.charAt(7)) + String.valueOf(antiguedad.charAt(8)) + String.valueOf(antiguedad.charAt(9));
        SimpleDateFormat formatofecha = new SimpleDateFormat("yyyy", Locale.getDefault());
        Date date = new Date();
        String añoactual = formatofecha.format(date);
        int ant = (Integer.parseInt(añoactual) - Integer.parseInt(año));
        cuantiaAnt.setText(Integer.toString(ant));
        deduccionANT.setText(Integer.toString(ant*20));

        Double total = km + ant + Double.parseDouble(DatosUsuario.getSueldo());
        totalNomina.setText(Double.toString(total));

        /*Gestionamos la descarga de datos en formato pdf*/
        btnDescargar = root.findViewById(R.id.btnDescargar);
        btnDescargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return root;

    }

}