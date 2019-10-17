package com.example.goazen.Administrador.ui.trabajadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        public ImageView portada;
        public TextView titulo;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


}

@NonNull
@Override
public ViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {

}
