package com.example.goazen.Administrador.ui.trabajadores;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goazen.R;

import java.util.Objects;

public class TrabajadoresFragment extends Fragment {

    private TrabajadoresViewModel trabajadoresViewModel;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutmanager;
    private adaptador_recycler_trabajador adaptador;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        trabajadoresViewModel =
                ViewModelProviders.of(this).get(TrabajadoresViewModel.class);
        View root = inflater.inflate(R.layout.fragment_trabajadores, container, false);

        recyclerView = root.findViewById(R.id.recyclerTrabajadores);
        adaptador = new adaptador_recycler_trabajador(Objects.requireNonNull(getContext()), Trabajador.getTrabajadores());
        recyclerView.setAdapter(adaptador);

        layoutmanager = new GridLayoutManager(getContext(), 4);
        recyclerView.setLayoutManager(layoutmanager);

        return root;
    }
}