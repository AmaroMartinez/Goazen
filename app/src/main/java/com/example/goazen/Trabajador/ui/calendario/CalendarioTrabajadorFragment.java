package com.example.goazen.Trabajador.ui.calendario;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goazen.R;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import java.util.Date;

public class CalendarioTrabajadorFragment extends Fragment {

    private CalendarioTrabajadorViewModel calendarioTrabajadorViewModelViewModel;
    private RecyclerView Rv_Cal_trabajador;
    private RecyclerView.LayoutManager layoutManager;
    private Adaptador_Cal_Trabajador adaptador;
    private CompactCalendarView calendario;
    private TextView tv_Dia;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        calendarioTrabajadorViewModelViewModel =
                ViewModelProviders.of(this).get(CalendarioTrabajadorViewModel.class);
        View root = inflater.inflate(R.layout.fragment_calendario_trabajador, container, false);
        final TextView textView = root.findViewById(R.id.tv_dia_calendario);

        calendarioTrabajadorViewModelViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        Rv_Cal_trabajador = root.findViewById(R.id.recyclerCalendarioTrabajador);

        tv_Dia = root.findViewById(R.id.tv_dia_calendario);
        calendario = root.findViewById(R.id.calendario_trabajador);
        calendario.setUseThreeLetterAbbreviation(true);
        calendario.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {

                EventosCalendario.setListaEventosDias(EventosCalendario.getListaEventos(), dateClicked);
                tv_Dia.setText(String.format("DIA :" + dateClicked.getDate()));
                adaptador = new Adaptador_Cal_Trabajador(getContext(),EventosCalendario.getListaEventosDias());
                Rv_Cal_trabajador.setAdapter(adaptador);
                layoutManager = new LinearLayoutManager(getContext());
                Rv_Cal_trabajador.setLayoutManager(layoutManager);

            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {

            }
        });

        return root;
    }
}