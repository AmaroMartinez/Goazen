package com.example.goazen.Administrador.ui.servicios;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.goazen.R;
import com.example.goazen.Servicios;
import com.example.goazen.Values;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


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

        /*Inicializamos los botones
         * Para ello primero nos descargamos de la base de datos los datos y despues
         * configuramos su estado dependiendo de lo que diga la descarga.*/
        ConfigurarPantalla();

        Log.e(Values.tag_log, "longitud lista: " + ListaServicios.size() + ". Tras configurar");
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
       Map<String, Object> datos = new HashMap<>();
        double precio = 0;
        for (int pos =0;pos<ListaServicios.size();pos++){
            if(ListaServicios.get(pos).getNombre_servicio().equals(servicio)){
             precio = ListaServicios.get(pos).getPrecio();
            }
        }
        datos.put("Precio",precio);
        datos.put("Enable", estado);
        Servicios.document(servicio).set(datos);

    }


    public void ConfigurarPantalla (){
        Log.e(Values.tag_log, "entramos en configurarPantalla");
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
                                Log.d(Values.tag_log,document.getId() +" --> "+ document.getBoolean("Enable")+" --> "+document.getDouble("Precio"));
                                ListaServicios.add(new Servicios(document.getId(),document.getBoolean("Enable"), document.getDouble("Precio")));
                                Log.e(Values.tag_log, String.valueOf(ListaServicios.size()));
                            }
                        }
                        if(task.isComplete()){
                            for(int pos = 0; pos<ListaServicios.size(); pos++){
                                Log.e(Values.tag_log,"Entramos en el bucle");
                                switch(ListaServicios.get(pos).getNombre_servicio()) {
                                    case Values.SERV_GENERAL:
                                        Log.d(Values.tag_log, "Hemos entrado en el case limpieza general");
                                        Sw_servicio_admin_limpieza_general.setChecked(ListaServicios.get(pos).getEnable());
                                        break;
                                    case Values.SERV_CRISTALES:
                                        Sw_servicio_admin_limpieza_cristales.setChecked(ListaServicios.get(pos).getEnable());
                                        break;
                                    case Values.SERV_COCINA:
                                        Sw_servicio_admin_cocina.setChecked(ListaServicios.get(pos).getEnable());
                                        break;
                                    case Values.SERV_LAVANDERIA:
                                        Sw_servicio_admin_lavanderia.setChecked(ListaServicios.get(pos).getEnable());
                                        break;
                                    case Values.SERV_PASEO:
                                        Sw_servicio_admin_paseo_mascotas.setChecked(ListaServicios.get(pos).getEnable());
                                        break;
                                    case Values.SERV_PLANCHA:
                                        Sw_servicio_admin_plancha.setChecked(ListaServicios.get(pos).getEnable());
                                        break;
                                    case Values.SERV_REGADO:
                                        Sw_servicio_admin_regado_plantas.setChecked(ListaServicios.get(pos).getEnable());
                                        break;
                                    default:
                                        break;
                                }
                            }

                        }else{
                            Log.d(Values.tag_log, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
}