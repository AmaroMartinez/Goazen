package com.example.goazen.ui.perfil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.goazen.PopUpCambiarContrasena;
import com.example.goazen.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

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
        readNewUsuario();


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

                btneditar.setText(R.string.st_perfil_cancelar);

            } else if (btneditar.getText().equals("Cancelar")){

                etnombre.setText(R.string.st_perfil_nombre);
                etapellido.setText(R.string.st_perfil_apellido);
                etdireccion.setText(R.string.st_perfil_d_direccion);
                etdni.setText(R.string.st_perfil_d_dni);
                etemail.setText(R.string.st_perfil_d_email);
                etfnacimiento.setText(R.string.st_perfil_d_fnacimiento);
                ettelefono.setText(R.string.st_perfil_d_telefono);

                etnombre.setEnabled(false);
                etapellido.setEnabled(false);
                etdireccion.setEnabled(false);
                etdni.setEnabled(false);
                etemail.setEnabled(false);
                etfnacimiento.setEnabled(false);
                ettelefono.setEnabled(false);
                btncambiarcontrasena.setEnabled(false);

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

                btneditar.setText(R.string.st_perfil_editar);
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


    private void writeNewUsuario(){

        CollectionReference Usuarios = db.collection("Usuarios");

        Map<String, Object> datos = new HashMap<>();
        datos.put("Nombre", "Tus");
        datos.put("Apellido", "Muertos");
        //data1.put("regions", Arrays.asList("west_coast", "norcal"));
        Usuarios.document("U").set(datos
        );
    }

    private void readNewUsuario(){
        DocumentReference docRef = db.collection("Usuarios").document("usu1");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        System.out.println(document.getString("Nombre"));
                        System.out.println(document.getString("Apellido"));
                        System.out.println(document.getString("DNI"));
                        System.out.println(document.getString("Adress"));
                        System.out.println(document.getString("Contrasena"));
                        System.out.println(document.getLong("c_bancaria"));
                        System.out.println(document.getString("email"));
                        System.out.println(document.getLong("movil"));
                        System.out.println(document.getString("usu_tipo"));
                        System.out.println(document.getString("fnacimiento"));







                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }



}