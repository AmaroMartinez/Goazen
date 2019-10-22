package com.example.goazen.Administrador.ui.trabajadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goazen.R;

import java.util.List;

public class adaptador_recycler_trabajador extends RecyclerView.Adapter<adaptador_recycler_trabajador.ViewHolder> {

    private LayoutInflater inflador;
    protected List<Trabajador> trabajadoresList;
    private Context contexto;

    private View.OnClickListener onClickListener;

    public adaptador_recycler_trabajador (Context contexto, List<Trabajador> trabajadoresList) {
        inflador = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
        this.trabajadoresList = trabajadoresList;
        this.contexto = contexto;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nombre;
        public TextView apellido;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            apellido = itemView.findViewById(R.id.apellido_trabajador);
            nombre = itemView.findViewById(R.id.nombre_trabajador);


        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        View v = inflador.inflate(R.layout.contenido_recycler_trabajador,null);
        v.setOnClickListener(onClickListener);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder (@NonNull ViewHolder holder, int posicion) {
        Trabajador trabajador = trabajadoresList.get(posicion);
        holder.nombre.setText(trabajador.getNombre());
        holder.apellido.setText(trabajador.getApellido());
    }

    @Override
    public int getItemCount () { return trabajadoresList.size(); }

    public void setOnItemClickListener (View.OnClickListener onItemClickListener) {
        this.onClickListener = onClickListener;
    }


}


