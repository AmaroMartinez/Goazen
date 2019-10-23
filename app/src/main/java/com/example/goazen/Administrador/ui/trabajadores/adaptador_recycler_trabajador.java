package com.example.goazen.Administrador.ui.trabajadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
    private View.OnLongClickListener onLongClickListener;


    public adaptador_recycler_trabajador (Context contexto, List<Trabajador> trabajadoresList) {
        inflador = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
        this.trabajadoresList = trabajadoresList;
        this.contexto = contexto;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nombre;
        public TextView dni;
        public ImageView imagen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imagen = itemView.findViewById(R.id.imagenPerfilTrabajadores);
            dni = itemView.findViewById(R.id.dni_trabajador);
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
        holder.dni.setText(trabajador.getDni());
    }

    @Override
    public int getItemCount () { return trabajadoresList.size(); }

    public void setOnItemClickListener (View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }


}


