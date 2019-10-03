package com.example.goazen.ui.servicios;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.goazen.R;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Calendario extends AppCompatActivity {

    /*Declaramos las variables necesarias para el calendario*/
    CompactCalendarView calendario;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("mm - yyyy", Locale.getDefault());

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

            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                actionBar.setTitle(dateFormatMonth.format(firstDayOfNewMonth));
            }
        });
    }
}
