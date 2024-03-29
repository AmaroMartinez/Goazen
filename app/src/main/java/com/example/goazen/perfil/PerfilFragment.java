package com.example.goazen.perfil;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.goazen.DatosUsuario;
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
    private ImageButton imagenperfil;
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
        imagenperfil = root.findViewById(R.id.ib_cambiar_imagen);

        //Recoger los datos de la base de datos
        readUsuario();

        switch (DatosUsuario.getUsu_tipo()) {
            case "Trabajador":
                imagenperfil.setBackgroundResource(R.drawable.trabajador);
                break;
            case "Admin":
                imagenperfil.setBackgroundResource(R.drawable.libro);
                break;
            default:
                imagenperfil.setBackgroundResource(R.drawable.usuario);
                break;
        }

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

        DatosUsuario.setNombre(etnombre.getText().toString());
        DatosUsuario.setApellido(etapellido.getText().toString());
        DatosUsuario.setAdress(etdireccion.getText().toString());
        DatosUsuario.setDNI(etdni.getText().toString());
        DatosUsuario.setEmail(etemail.getText().toString());
        DatosUsuario.setFnacimiento(etfnacimiento.getText().toString());
        DatosUsuario.setMovil(ettelefono.getText().toString());

        DatosUsuario.writeUsuario();
    }

    private void readUsuario(){

        etnombre.setText(DatosUsuario.getNombre());
        etapellido.setText(DatosUsuario.getApellido());
        etdireccion.setText(DatosUsuario.getAdress());
        etdni.setText(DatosUsuario.getDNI());
        etemail.setText(DatosUsuario.getEmail());
        etfnacimiento.setText(DatosUsuario.getFnacimiento());
        ettelefono.setText(DatosUsuario.getMovil());


    }



}