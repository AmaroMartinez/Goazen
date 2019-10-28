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
import com.example.goazen.Values;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class NominasFragment extends Fragment {

    private NominasViewModel nominaViewModel;
    private FirebaseFirestore db;
    private Button btnEditar;
    private Button btnGuardar;
    private EditText domicilio;
    private EditText trabajador;
    private EditText dni;
    private EditText ss;
    private EditText fecha_de_ingreso;
    private TextView deduccionSB;
    private TextView deduccionKM;
    private TextView deduccionANT;
    private EditText totalNomina;
    private String TAG = "hola";
    private long calcularSB = 0;
    private long calcularKM = 0;
    private long calcularANT = 0;
    private long calcularTotal = 0;


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
        /*db = FirebaseFirestore.getInstance();

        //Enlazamod los textos con nuestras variables
        domicilio = root.findViewById(R.id.et_direccion);
        trabajador = root.findViewById(R.id.et_nombre_apellido);
        dni = root.findViewById(R.id.dni_et);
        ss = root.findViewById(R.id.et_nombre_apellido);
        fecha_de_ingreso = root.findViewById(R.id.et_fecha_ingreso);
        deduccionSB = root.findViewById(R.id.deduccionSB);
        deduccionKM = root.findViewById(R.id.deduccionKM);
        deduccionANT = root.findViewById(R.id.deduccionANT);
        totalNomina = root.findViewById(R.id.et_total);

        //Cargamos lo datos de la base de datos, para anexarlos a los textos.
        final DocumentReference docRef = db.collection("nomina").document("Aritz");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(Values.tag_log, "DocumentSnapshot data: " + document.getData());
                        domicilio.setText(document.getString("domicilio"));
                        trabajador.setText(document.getString("trabajador"));
                        dni.setText(document.getString("dni"));
                        ss.setText(document.getString("ss"));
                        fecha_de_ingreso.setText(document.getString("fecha_de_ingreso"));
                        deduccionSB.setText(String.valueOf(document.getLong("salario_base")));
                        deduccionKM.setText(String.valueOf(document.getLong("kilometraje")));
                        deduccionANT.setText(String.valueOf(document.getLong("antiguedad")));
                        calcularSB = document.getLong("salario_base");
                        calcularKM = document.getLong("kilometraje");
                        calcularANT = document.getLong("antiguedad");

                    } else {
                        Log.d(Values.tag_log, "No such document");
                    }
                }
                if (task.isComplete()){
                    calcularTotal = calcularSB + calcularKM + calcularANT;
                    totalNomina.setText(String.valueOf(calcularTotal));
                }
                else {
                    Log.d(Values.tag_log, "get failed with ", task.getException());
                }
            }
        });*/

        // Access a Cloud Firestore instance from your Activity
       /* db = FirebaseFirestore.getInstance();*/

        btnGuardar = root.findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                domicilio.setEnabled(false);
                trabajador.setEnabled(false);
                dni.setEnabled(false);
                ss.setEnabled(false);
                fecha_de_ingreso.setEnabled(false);

                // Creamos un usuario con atributos
               /* Map<String, Object> user = new HashMap<>();
                user.put("domicilio", domicilio.getText().toString());
                user.put("trabajador", trabajador.getText().toString());
                user.put("dni", dni.getText().toString());
                user.put("ss", ss.getText().toString());
                user.put("fecha_de_ingreso", fecha_de_ingreso.getText().toString());
                user.put("salario_base", calcularSB);
                user.put("kilometraje",calcularKM);
                user.put("antiguedad", calcularANT);

                //Añadimos un nuevo d ocumento con un ID generado
                db.collection("nomina").document("Aritz")
                        .set(user)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "DocumentSnapshot successfully written!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error writing document", e);
                            }
                        });*/
            }
        });

        return root;

    }

}