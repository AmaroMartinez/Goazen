package com.example.goazen.Administrador.ui.servicios;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.goazen.R;
import com.example.goazen.Servicios;
import com.example.goazen.Trabajador.ui.calendario.RecyclerViewCalTrabajador;
import com.example.goazen.Values;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;

import static android.content.ContentValues.TAG;

public class AdminServiciosFragment extends Fragment {

    private AdminServiciosViewModel adminServiciosViewModel;

    /*Declaramos los Switch que tenemos en el XML para poder programarlos*/
    private Switch Sw_servicio_admin_limpieza_general;
    private Switch Sw_servicio_admin_limpieza_cristales;
    private Switch Sw_servicio_admin_lavanderia;
    private Switch Sw_servicio_admin_paseo_mascotas;
    private Switch Sw_servicio_admin_regado_plantas;
    private Switch Sw_servicio_admin_plancha;
    private Switch Sw_servicio_admin_cocina;

    //Declaramos la conexión a la base de datos
    private static FirebaseFirestore db;

    //Campos a descargar de la base de datos;
    private ArrayList<Servicios> ListaServicios;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        adminServiciosViewModel =
                ViewModelProviders.of(this).get(AdminServiciosViewModel.class);
        View root = inflater.inflate(R.layout.fragment_admin_servicios, container, false);

        //Inicializamos todas las opciones de servicios existentes
        Sw_servicio_admin_limpieza_general = root.findViewById(R.id.sw_servicio_admin_limpieza_general);
        Sw_servicio_admin_limpieza_cristales = root.findViewById(R.id.sw_servicio_admin_limpieza_cristales);
        Sw_servicio_admin_lavanderia = root.findViewById(R.id.sw_servicio_admin_lavanderia);
        Sw_servicio_admin_paseo_mascotas = root.findViewById(R.id.sw_servicio_admin_paseo_mascotas);
        Sw_servicio_admin_regado_plantas = root.findViewById(R.id.sw_servicio_admin_regado_plantas);
        Sw_servicio_admin_plancha = root.findViewById(R.id.sw_servicio_admin_plancha);
        Sw_servicio_admin_cocina = root.findViewById(R.id.sw_servicio_admin_cocina);

        /*Inicializamos los botones*/
        ConfigurarPantalla();
        for(int pos = 0; pos<ListaServicios.size(); pos++){
            switch(ListaServicios.get(pos).getNombre_servicio()){
                case getString(R.string.st_limpieza_general):
                    Sw_servicio_admin_limpieza_general.setChecked(ListaServicios.get(pos).isEnable());
                    break;
                default:

            }


        }
        /*Programamos la funcionalidad de cada uno de ellos teniendo en cuenta que cada vez que el
        * estado cambie se actualizara en la base de datos.*/

        Sw_servicio_admin_limpieza_general.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                actualizarEstado(Sw_servicio_admin_limpieza_general.getText().toString(),isChecked);
            }
        });

        return root;
    }

    public void actualizarEstado(String servicio, boolean estado){
        //Conectamos
        db = FirebaseFirestore.getInstance();
        //Indicamos la colección a la que vamos a acceder
        CollectionReference Servicios = db.collection("Servicios");

    }

    public void ConfigurarPantalla (){
        ListaServicios = new ArrayList<Servicios>();
        db = FirebaseFirestore.getInstance();
        db.collection("Servicios")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                /*Cogemos los datos de cada uno de los servicios y los introducimo
                                en el array*/
                                ListaServicios.add(new Servicios(document.getId().toString(),document.getBoolean("Enable"), document.getDouble("precio")));
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
}