package com.example.goazen.Trabajador.ui.calendario;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goazen.R;

import java.util.ArrayList;

/*PASO 4 --> RECYCLER VIEW*/
/*Creamos una clase java para generar el adaptador del RecyclerView.
*
* Es importante que extienda de la clase adapter parr que funcione*/

public class Adaptador_Cal_Trabajador extends RecyclerView.Adapter<Adaptador_Cal_Trabajador.ViewHolder> {

    /*Declaramos los objetos necesarios para poder inflar
    * el layout*/
    private LayoutInflater inflador;
    protected ArrayList<RecyclerViewCalTrabajador> lista;
    private Context context;

    /*Contructor del adaptador, que se utiliza para poder declarar la
    * lista de objetos que  va a contener el RecyclerView.*/
    public Adaptador_Cal_Trabajador(Context context, ArrayList<RecyclerViewCalTrabajador> lista){
        inflador = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        this.lista = lista;
        this.context = context;
    }


    /*Creamos una clase interna para el ViewHolder.
    * Que nos va a servir para indicar que es lo que
    * se va a mostrar*/
    public static class ViewHolder extends RecyclerView.ViewHolder{

        /*Declaramos los objetos del XML contenedor del recyclew view*/
        public TextView tarea;
        public TextView hora;
        public TextView direccion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tarea = itemView.findViewById(R.id.tv_tarea_trabajador);
            hora = itemView.findViewById(R.id.tv_hora);
            direccion = itemView.findViewById(R.id.tv_direccion);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflador.inflate(R.layout.eventos_cal_trabajador,null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecyclerViewCalTrabajador rvct = lista.get(position);
        holder.tarea.setText(rvct.getSt_tarea_trabajador());
        holder.hora.setText(rvct.getSt_hora_trabajo());
        holder.direccion.setText(rvct.getSt_dir_trabajo());
    }


    @Override
    public int getItemCount() {
        return lista.size();
    }
}
