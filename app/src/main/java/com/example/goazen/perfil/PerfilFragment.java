package com.example.goazen.perfil;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.goazen.DatosCliente;
import com.example.goazen.R;
import com.google.firebase.firestore.FirebaseFirestore;

public class PerfilFragment extends Fragment {

    private PerfilViewModel perfilViewModel;
    private Button btneditar;
    private EditText etnombre;
    private EditText etapellido;
    private EditText etfnacimiento;
    private EditText ettelefono;
    private EditText etdni;
    private EditText etemail;
    private EditText etdireccion;
    private Button btncambiarcontrasena;
    private Button btnguardar;
    private FirebaseFirestore db;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        perfilViewModel =
                ViewModelProviders.of(this).get(PerfilViewModel.class);
        View root = inflater.inflate(R.layout.fragment_perfil, container, false);

        db = FirebaseFirestore.getInstance();

        btneditar = root.findViewById(R.id.btn_editar);
        etnombre = root.findViewById(R.id.et_perfil_nombre);
        etapellido = root.findViewById(R.id.et_perfil_apellido);
        etfnacimiento = root.findViewById(R.id.et_fnacimiento);
        ettelefono = root.findViewById(R.id.et_telefono);
        etdni = root.findViewById(R.id.et_dni);
        etemail = root.findViewById(R.id.et_email);
        etdireccion = root.findViewById(R.id.et_direccion);
        btncambiarcontrasena = root.findViewById(R.id.btn_cambiar_contrasena);
        btnguardar = root.findViewById(R.id.btn_guardar);

        //Recoger los datos de la base de datos
        readUsuario();


        btneditar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (btneditar.getText().equals("Editar")) {
                etnombre.setEnabled(true);
                etapellido.setEnabled(true);
                etdireccion.setEnabled(true);
                etdni.setEnabled(true);
                etemail.setEnabled(true);
                etfnacimiento.setEnabled(true);
                ettelefono.setEnabled(true);
                btncambiarcontrasena.setEnabled(true);
                btnguardar.setEnabled(true);

                btneditar.setText(R.string.st_perfil_cancelar);

            } else if (btneditar.getText().equals("Cancelar")){

                readUsuario();

                etnombre.setEnabled(false);
                etapellido.setEnabled(false);
                etdireccion.setEnabled(false);
                etdni.setEnabled(false);
                etemail.setEnabled(false);
                etfnacimiento.setEnabled(false);
                ettelefono.setEnabled(false);
                btncambiarcontrasena.setEnabled(false);
                btnguardar.setEnabled(false);

                btneditar.setText(R.string.st_perfil_editar);

            }

         }
        });

        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etnombre.setEnabled(false);
                etapellido.setEnabled(false);
                etdireccion.setEnabled(false);
                etdni.setEnabled(false);
                etemail.setEnabled(false);
                etfnacimiento.setEnabled(false);
                ettelefono.setEnabled(false);
                btncambiarcontrasena.setEnabled(false);
                btnguardar.setEnabled(false);

                btneditar.setText(R.string.st_perfil_editar);

                modificarUsuario();
            }
        });

        btncambiarcontrasena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent popUpcc = new Intent(getContext(), PopUpCambiarContrasena.class);
                startActivity(popUpcc);
            }
        });





        return root;
    }

    private void modificarUsuario(){

        DatosCliente.setNombre(etnombre.getText().toString());
        DatosCliente.setApellido(etapellido.getText().toString());
        DatosCliente.setAdress(etdireccion.getText().toString());
        DatosCliente.setDNI(etdni.getText().toString());
        DatosCliente.setEmail(etemail.getText().toString());
        DatosCliente.setFnacimiento(etfnacimiento.getText().toString());
        DatosCliente.setMovil(ettelefono.getText().toString());

        DatosCliente.writeUsuario();
    }

    private void readUsuario(){

        etnombre.setText(DatosCliente.getNombre());
        etapellido.setText(DatosCliente.getApellido());
        etdireccion.setText(DatosCliente.getAdress());
        etdni.setText(DatosCliente.getDNI());
        etemail.setText(DatosCliente.getEmail());
        etfnacimiento.setText(DatosCliente.getFnacimiento());
        ettelefono.setText(DatosCliente.getMovil());


    }



}