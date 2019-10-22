package com.example.goazen.Administrador.ui.servicios;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.goazen.R;

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

        /*Programamos la funcionalidad de cada uno de ellos teniendo en cuenta que cada vez que el
        * estado cambie se actualizara en la base de datos.
        * */

       

        return root;
    }
}