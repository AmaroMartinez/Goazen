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
    private TextView cuantiakm;
    private long calcularSB = 0;
    private long calcularKM = 0;
    private long calcularANT = 0;
    private long calcularTotal = 0;
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