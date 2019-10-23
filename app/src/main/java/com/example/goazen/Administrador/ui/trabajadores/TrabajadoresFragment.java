package com.example.goazen.Administrador.ui.trabajadores;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goazen.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;

public class TrabajadoresFragment extends Fragment {

    private TrabajadoresViewModel trabajadoresViewModel;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutmanager;
    private adaptador_recycler_trabajador adaptador;
    private FloatingActionButton creartrabajador;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        trabajadoresViewModel =
                ViewModelProviders.of(this).get(TrabajadoresViewModel.class);
        View root = inflater.inflate(R.layout.fragment_trabajadores, container, false);
        Trabajador.leertrabajadores();
        recyclerView = root.findViewById(R.id.recyclerTrabajadores);
        creartrabajador = root.findViewById(R.id.crear_trabajador);
        ArrayList <Trabajador> trabajadors = Trabajador.getTrabajadores();
        Log.d("tag", "trabajadores? " + trabajadors.size());
        adaptador = new adaptador_recycler_trabajador(Objects.requireNonNull(getContext()), trabajadors);
        adaptador.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), ModificarTrabajadorActivity.class);
                startActivity(myIntent);
            }
        });
        recyclerView.setAdapter(adaptador);

        layoutmanager = new GridLayoutManager(getContext(), 4);
        recyclerView.setLayoutManager(layoutmanager);

        creartrabajador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), CrearCuentaTrabajadorActivity.class);
                startActivity(myIntent);
            }
        });

        return root;
    }
}