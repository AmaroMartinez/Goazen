package com.example.goazen.ui.servicios;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.goazen.R;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Calendario extends AppCompatActivity {

    /*Declaramos las variables necesarias para el calendario*/
    CompactCalendarView calendario;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("dd - mm - yyyy", Locale.getDefault());

    //Declaramos las variables necesarias para el guardado de datos.
    int dia;

    //Declaramos los objetos necesarios
    TextView tv = (TextView)findViewById(R.id.tv_calendario);
    RadioGroup rg = (RadioGroup)findViewById(R.id.rg_calendario);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setTitle(null);

        //Inicializamos el calendario
        calendario = (CompactCalendarView)findViewById(R.id.compactcalendar_view);

        calendario.setUseThreeLetterAbbreviation(true);


        //Añadimos un listener para las acciones.
        calendario.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {

                //Guaradamos el día
                dia = dateClicked.getDay();

                //Hacemos visibles las horas disponibles
                tv.setVisibility(View.VISIBLE);
                rg.setVisibility(View.VISIBLE);

            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                actionBar.setTitle(dateFormatMonth.format(firstDayOfNewMonth));
            }
        });

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

            }
        });

    }
}
